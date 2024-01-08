/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017-2024 Daniel Alievsky, AlgART Laboratory (http://algart.net)
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

package net.algart.executors.modules.opencv.matrices.filtering;

import net.algart.executors.modules.opencv.common.UMatFilter;
import net.algart.executors.modules.opencv.util.enums.OBorderType;
import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.global.opencv_imgproc;

import java.util.Locale;

public final class Blur extends UMatFilter {
    private int kernelSizeX = 15;
    private int kernelSizeY = 0;
    private OBorderType borderType = OBorderType.BORDER_DEFAULT;

    public int getKernelSizeX() {
        return kernelSizeX;
    }

    public Blur setKernelSizeX(int kernelSizeX) {
        this.kernelSizeX = nonNegative(kernelSizeX);
        return this;
    }

    public Blur setKernelSizeX(String kernelSizeX) {
        return setKernelSizeX(intOrDefault(kernelSizeX, 0));
    }

    public int getKernelSizeY() {
        return kernelSizeY;
    }

    public Blur setKernelSizeY(int kernelSizeY) {
        this.kernelSizeY = nonNegative(kernelSizeY);
        return this;
    }

    public Blur setKernelSizeY(String kernelSizeY) {
        return setKernelSizeY(intOrDefault(kernelSizeY, 0));
    }

    public OBorderType getBorderType() {
        return borderType;
    }

    public Blur setBorderType(OBorderType borderType) {
        this.borderType = nonNull(borderType);
        return this;
    }

    /*Repeat() Mat ==> UMat */
    @Override
    public Mat process(Mat source) {
        int sizeX = kernelSizeX > 0 ? kernelSizeX : kernelSizeY;
        int sizeY = kernelSizeY > 0 ? kernelSizeY : kernelSizeX;
        if (sizeX == 0 && sizeY == 0) {
            return source;
        }
        long t1 = debugTime();
        try (Point anchor = new Point(-1, -1)) {
            try (Size size = new Size(sizeX, sizeY)) {
                opencv_imgproc.blur(source, source, size, anchor, getBorderType().code());
            }
        }
        long t2 = debugTime();
        logDebug(() -> String.format(Locale.US, "Simple OpenCV blur of %s calculated in %.3f ms",
                source, (t2 - t1) * 1e-6));
        return source;

    }

    /*Repeat.AutoGeneratedStart !! Auto-generated: NOT EDIT !! */
    @Override
    public UMat process(UMat source) {
        int sizeX = kernelSizeX > 0 ? kernelSizeX : kernelSizeY;
        int sizeY = kernelSizeY > 0 ? kernelSizeY : kernelSizeX;
        if (sizeX == 0 && sizeY == 0) {
            return source;
        }
        long t1 = debugTime();
        try (Point anchor = new Point(-1, -1)) {
            try (Size size = new Size(sizeX, sizeY)) {
                opencv_imgproc.blur(source, source, size, anchor, getBorderType().code());
            }
        }
        long t2 = debugTime();
        logDebug(() -> String.format(Locale.US, "Simple OpenCV blur of %s calculated in %.3f ms",
                source, (t2 - t1) * 1e-6));
        return source;

    }

    /*Repeat.AutoGeneratedEnd*/
}
