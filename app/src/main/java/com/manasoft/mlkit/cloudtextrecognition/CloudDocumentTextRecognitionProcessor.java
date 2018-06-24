// Copyright 2018 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.manasoft.mlkit.cloudtextrecognition;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.cloud.text.FirebaseVisionCloudDocumentTextDetector;
import com.google.firebase.ml.vision.cloud.text.FirebaseVisionCloudText;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.manasoft.mlkit.FrameMetadata;
import com.manasoft.mlkit.GraphicOverlay;
import com.manasoft.mlkit.VisionProcessorBase;

/** Processor for the cloud document text detector demo. */
public class CloudDocumentTextRecognitionProcessor
    extends VisionProcessorBase<FirebaseVisionCloudText> {

  private static final String TAG = "CloudDocTextRecProc";

  private final FirebaseVisionCloudDocumentTextDetector detector;

  public CloudDocumentTextRecognitionProcessor() {
    super();
    detector = FirebaseVision.getInstance().getVisionCloudDocumentTextDetector();
  }

  @Override
  protected Task<FirebaseVisionCloudText> detectInImage(FirebaseVisionImage image) {
    return detector.detectInImage(image);
  }

  @Override
  protected void onSuccess(
      @NonNull FirebaseVisionCloudText text,
      @NonNull FrameMetadata frameMetadata,
      @NonNull GraphicOverlay graphicOverlay) {
    graphicOverlay.clear();
      Log.d(TAG, "detected text is: " + text.getText());
      CloudTextGraphic textGraphic = new CloudTextGraphic(graphicOverlay, text);
      graphicOverlay.add(textGraphic);
  }

  @Override
  protected void onFailure(@NonNull Exception e) {
    Log.w(TAG, "Cloud Document Text detection failed." + e);
  }
}
