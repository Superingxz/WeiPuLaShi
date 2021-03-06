/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xolo.weipulashi.zxing.encode;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.PhoneNumberUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.result.AddressBookParsedResult;
import com.google.zxing.common.BitMatrix;
import com.xolo.weipulashi.utils.DensityUtils;
import com.xolo.weipulashi.zxing.Contents;
import com.xolo.weipulashi.zxing.decoding.Intents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * This class does the work of decoding the user's request and extracting all the data
 * to be encoded in a barcode.
 *
 * @author dswitkin@google.com (Daniel Switkin)
 */
public class QRCodeEncoder {

  private static final String TAG = QRCodeEncoder.class.getSimpleName();

  private static final int WHITE = 0xFFFFFFFF;
  private static final int BLACK = 0xFF000000;

  private final Context activity;
  private String contents;
  private String displayContents;
  private String title;
  private BarcodeFormat format;
  private final int dimension;
  private final boolean useVCard;

  public QRCodeEncoder(Context activity, String text, int dimension, boolean useVCard) throws WriterException {
    Intent intent = new Intent(Intents.Encode.ACTION);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
    intent.putExtra(Intents.Encode.TYPE, Contents.Type.TEXT);
    intent.putExtra(Intents.Encode.DATA, text);
    intent.putExtra(Intents.Encode.FORMAT, BarcodeFormat.QR_CODE.toString());

    this.activity = activity;
    this.dimension = DensityUtils.dp2px(activity,dimension);
    this.useVCard = useVCard;
    encodeContentsFromZXingIntent(intent);
  }

  String getTitle() {
    return title;
  }


  // It would be nice if the string encoding lived in the core ZXing library,
  // but we use platform specific code like PhoneNumberUtils, so it can't.
  private boolean encodeContentsFromZXingIntent(Intent intent) {
     // Default to QR_CODE if no format given.
    String formatString = intent.getStringExtra(Intents.Encode.FORMAT);
    format = null;
    if (formatString != null) {
      try {
        format = BarcodeFormat.valueOf(formatString);
      } catch (IllegalArgumentException iae) {
        // Ignore it then
      }
    }
    if (format == null || format == BarcodeFormat.QR_CODE) {
      String type = intent.getStringExtra(Intents.Encode.TYPE);
      if (type == null || type.isEmpty()) {
        return false;
      }
      this.format = BarcodeFormat.QR_CODE;
      encodeQRCodeContents(intent, type);
    } else {
      String data = intent.getStringExtra(Intents.Encode.DATA);
      if (data != null && !data.isEmpty()) {
        contents = data;
        displayContents = data;
      }
    }
    return contents != null && !contents.isEmpty();
  }


  private void encodeQRCodeContents(Intent intent, String type) {
    switch (type) {
      case Contents.Type.TEXT: {
        String data = intent.getStringExtra(Intents.Encode.DATA);
        if (data != null && !data.isEmpty()) {
          contents = data;
          displayContents = data;
        }
        break;
      }
      case Contents.Type.EMAIL: {
        String data = ContactEncoder.trim(intent.getStringExtra(Intents.Encode.DATA));
        if (data != null) {
          contents = "mailto:" + data;
          displayContents = data;
        }
        break;
      }
      case Contents.Type.PHONE: {
        String data = ContactEncoder.trim(intent.getStringExtra(Intents.Encode.DATA));
        if (data != null) {
          contents = "tel:" + data;
          displayContents = PhoneNumberUtils.formatNumber(data);
        }
        break;
      }
      case Contents.Type.SMS: {
        String data = ContactEncoder.trim(intent.getStringExtra(Intents.Encode.DATA));
        if (data != null) {
          contents = "sms:" + data;
          displayContents = PhoneNumberUtils.formatNumber(data);
        }
        break;
      }
      case Contents.Type.CONTACT: {

        Bundle bundle = intent.getBundleExtra(Intents.Encode.DATA);
        if (bundle != null) {

          String name = bundle.getString(ContactsContract.Intents.Insert.NAME);
          String organization = bundle.getString(ContactsContract.Intents.Insert.COMPANY);
          String address = bundle.getString(ContactsContract.Intents.Insert.POSTAL);
          List<String> phones = getAllBundleValues(bundle, Contents.PHONE_KEYS);
          List<String> phoneTypes = getAllBundleValues(bundle, Contents.PHONE_TYPE_KEYS);
          List<String> emails = getAllBundleValues(bundle, Contents.EMAIL_KEYS);
          String url = bundle.getString(Contents.URL_KEY);
          List<String> urls = url == null ? null : Collections.singletonList(url);
          String note = bundle.getString(Contents.NOTE_KEY);

          ContactEncoder encoder = useVCard ? new VCardContactEncoder() : new MECARDContactEncoder();
          String[] encoded = encoder.encode(Collections.singletonList(name),
                                            organization,
                                            Collections.singletonList(address),
                                            phones,
                                            phoneTypes,
                                            emails,
                                            urls,
                                            note);
          // Make sure we've encoded at least one field.
          if (!encoded[1].isEmpty()) {
            contents = encoded[0];
            displayContents = encoded[1];
          }

        }

        break;
      }
      case Contents.Type.LOCATION: {
        Bundle bundle = intent.getBundleExtra(Intents.Encode.DATA);
        if (bundle != null) {
          // These must use Bundle.getFloat(), not getDouble(), it's part of the API.
          float latitude = bundle.getFloat("LAT", Float.MAX_VALUE);
          float longitude = bundle.getFloat("LONG", Float.MAX_VALUE);
          if (latitude != Float.MAX_VALUE && longitude != Float.MAX_VALUE) {
            contents = "geo:" + latitude + ',' + longitude;
            displayContents = latitude + "," + longitude;
          }
        }
        break;
      }
    }
  }

  private static List<String> getAllBundleValues(Bundle bundle, String[] keys) {
    List<String> values = new ArrayList<>(keys.length);
    for (String key : keys) {
      Object value = bundle.get(key);
      values.add(value == null ? null : value.toString());
    }
    return values;
  }

  private void encodeQRCodeContents(AddressBookParsedResult contact) {
    ContactEncoder encoder = useVCard ? new VCardContactEncoder() : new MECARDContactEncoder();
    String[] encoded = encoder.encode(toList(contact.getNames()),
                                      contact.getOrg(),
                                      toList(contact.getAddresses()),
                                      toList(contact.getPhoneNumbers()),
                                      null,
                                      toList(contact.getEmails()),
                                      toList(contact.getURLs()),
                                      null);
    // Make sure we've encoded at least one field.
    if (!encoded[1].isEmpty()) {
      contents = encoded[0];
      displayContents = encoded[1];
    }
  }

  private static List<String> toList(String[] values) {
    return values == null ? null : Arrays.asList(values);
  }

  public Bitmap encodeAsBitmap() throws WriterException {
    String contentsToEncode = contents;
    if (contentsToEncode == null) {
      return null;
    }
    Map<EncodeHintType,Object> hints = null;
    String encoding = guessAppropriateEncoding(contentsToEncode);
    if (encoding != null) {
      hints = new EnumMap<>(EncodeHintType.class);
      hints.put(EncodeHintType.CHARACTER_SET, encoding);
    }
    BitMatrix result;
    try {
      result = new MultiFormatWriter().encode(contentsToEncode, format, dimension, dimension, hints);
    } catch (IllegalArgumentException iae) {
      // Unsupported format
      return null;
    }
    int width = result.getWidth();
    int height = result.getHeight();
    int[] pixels = new int[width * height];
    for (int y = 0; y < height; y++) {
      int offset = y * width;
      for (int x = 0; x < width; x++) {
        pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
      }
    }

    Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
    return bitmap;
  }

  private static String guessAppropriateEncoding(CharSequence contents) {
    // Very crude at the moment
    for (int i = 0; i < contents.length(); i++) {
      if (contents.charAt(i) > 0xFF) {
        return "UTF-8";
      }
    }
    return null;
  }

}
