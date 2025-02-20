/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017-2025 Daniel Alievsky, AlgART Laboratory (http://algart.net)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package net.algart.executors.modules.opencv.matrices.features.detection;

import net.algart.executors.api.data.SNumbers;
import net.algart.executors.modules.opencv.common.UMatToNumbers;
import net.algart.executors.modules.opencv.util.O2SMat;
import net.algart.executors.modules.opencv.util.OTools;
import org.bytedeco.opencv.global.opencv_ximgproc;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;

public final class FastLineDetector extends UMatToNumbers {
    public static final String OUTPUT_LINES = "lines";
    public static final String OUTPUT_DRAWN_SEGMENTS = "drawn_segments";

    private int lengthThreshold = 10;
    private double distanceThreshold = 1.414;
    private double cannyTh1 = 50.0;
    private double cannyTh2 = 50.0;
    private int cannyApertureSize = 3;
    private boolean doMerge = false;
    private boolean drawFoundSegments = false;

    public FastLineDetector() {
        setDefaultOutputNumbers(OUTPUT_LINES);
        addOutputMat(OUTPUT_DRAWN_SEGMENTS);
    }

    public int getLengthThreshold() {
        return lengthThreshold;
    }

    public FastLineDetector setLengthThreshold(int lengthThreshold) {
        this.lengthThreshold = nonNegative(lengthThreshold);
        return this;
    }

    public double getDistanceThreshold() {
        return distanceThreshold;
    }

    public FastLineDetector setDistanceThreshold(double distanceThreshold) {
        this.distanceThreshold = distanceThreshold;
        return this;
    }

    public double getCannyTh1() {
        return cannyTh1;
    }

    public FastLineDetector setCannyTh1(double cannyTh1) {
        this.cannyTh1 = cannyTh1;
        return this;
    }

    public double getCannyTh2() {
        return cannyTh2;
    }

    public FastLineDetector setCannyTh2(double cannyTh2) {
        this.cannyTh2 = cannyTh2;
        return this;
    }

    public int getCannyApertureSize() {
        return cannyApertureSize;
    }

    public FastLineDetector setCannyApertureSize(int cannyApertureSize) {
        this.cannyApertureSize = nonNegative(cannyApertureSize);
        return this;
    }

    public boolean isDoMerge() {
        return doMerge;
    }

    public FastLineDetector setDoMerge(boolean doMerge) {
        this.doMerge = doMerge;
        return this;
    }

    public boolean isDrawFoundSegments() {
        return drawFoundSegments;
    }

    public FastLineDetector setDrawFoundSegments(boolean drawFoundSegments) {
        this.drawFoundSegments = drawFoundSegments;
        return this;
    }

    /*Repeat() \bMat ==> UMat */
    @Override
    public SNumbers analyse(Mat source) {
        Mat mat = source;
        try {
            mat = OTools.toMono8UIfNot(mat);
            try (final org.bytedeco.opencv.opencv_ximgproc.FastLineDetector detector =
                         opencv_ximgproc.createFastLineDetector(
                                 lengthThreshold,
                                 (float) distanceThreshold,
                                 cannyTh1,
                                 cannyTh2,
                                 cannyApertureSize,
                                 doMerge)) {
                try (Mat linesMat = new Mat()) {
                    detector.detect(mat, linesMat);
                    if (drawFoundSegments && isOutputNecessary(OUTPUT_DRAWN_SEGMENTS)) {
                        detector.drawSegments(source, linesMat);
                        O2SMat.setTo(getMat(OUTPUT_DRAWN_SEGMENTS), source);
                    }
                    return O2SMat.toRawNumbers(linesMat, 4);
                }
            }
        } finally {
            OTools.closeFirstIfDiffersFromSecond(mat, source);
        }
    }

    /*Repeat.AutoGeneratedStart !! Auto-generated: NOT EDIT !! */
    @Override
    public SNumbers analyse(UMat source) {
        UMat mat = source;
        try {
            mat = OTools.toMono8UIfNot(mat);
            try (final org.bytedeco.opencv.opencv_ximgproc.FastLineDetector detector =
                         opencv_ximgproc.createFastLineDetector(
                                 lengthThreshold,
                                 (float) distanceThreshold,
                                 cannyTh1,
                                 cannyTh2,
                                 cannyApertureSize,
                                 doMerge)) {
                try (UMat linesMat = new UMat()) {
                    detector.detect(mat, linesMat);
                    if (drawFoundSegments && isOutputNecessary(OUTPUT_DRAWN_SEGMENTS)) {
                        detector.drawSegments(source, linesMat);
                        O2SMat.setTo(getMat(OUTPUT_DRAWN_SEGMENTS), source);
                    }
                    return O2SMat.toRawNumbers(linesMat, 4);
                }
            }
        } finally {
            OTools.closeFirstIfDiffersFromSecond(mat, source);
        }
    }

    /*Repeat.AutoGeneratedEnd*/


    @Override
    protected boolean allowInputPackedBits() {
        return true;
    }
}
