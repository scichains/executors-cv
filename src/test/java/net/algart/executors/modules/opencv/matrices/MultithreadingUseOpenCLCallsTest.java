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

package net.algart.executors.modules.opencv.matrices;

import org.bytedeco.opencv.global.opencv_core;

import java.util.ArrayList;
import java.util.List;

public final class MultithreadingUseOpenCLCallsTest {
    static class SimpleExecutor {
        final int index;
        public SimpleExecutor(int index) {
            this.index = index;
        }
        public synchronized void execute() {
            final boolean haveOpenCL = opencv_core.haveOpenCL();
            final boolean useOpenCL = opencv_core.useOpenCL();
            System.out.println("Executor " + index + ": "
                    + (useOpenCL != haveOpenCL ? "!!! STRANGE SITUATION! " : "")
                    + "haveOpenCL: " + haveOpenCL + "; useOpenCL: " + useOpenCL);
            // - useOpenCL is cached without synchronization in OpenCV 4.0.0,
            // and it leads to incorrect results in most threads
            try {
                Thread.sleep(50);
            } catch (InterruptedException ignored) {
            }
        }
    }

    public static void main(String[] args) {
        final int numberOfTests = 150;
        for (int test = 1; test <= numberOfTests; test++) {
            System.out.printf("%nTest #%d/%d...%n", test, numberOfTests);
            List<SimpleExecutor> executors = new ArrayList<>();
            for (int k = 1; k <= 5; k++) {
                executors.add(new SimpleExecutor(k));
            }
            executors.parallelStream().forEach(SimpleExecutor::execute);
        }
    }
}
