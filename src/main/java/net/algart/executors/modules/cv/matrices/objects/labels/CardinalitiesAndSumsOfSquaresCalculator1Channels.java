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

package net.algart.executors.modules.cv.matrices.objects.labels;

abstract class CardinalitiesAndSumsOfSquaresCalculator1Channels extends CardinalitiesAndSumsOfSquaresCalculator {
    CardinalitiesAndSumsOfSquaresCalculator1Channels(int[] labels) {
        super(labels, 1);
    }

    /*Repeat() Bytes  ==> Shorts,,Ints,,Floats,,Doubles;;
               byte   ==> short,,int,,float,,double;;
               (data\w*\[k\]) \& 0xFF ==> $1 & 0xFFFF,,$1,,$1,,$1
     */
    static class ForBytes extends CardinalitiesAndSumsOfSquaresCalculator1Channels {
        private final byte[] data0;

        public ForBytes(int[] labels, byte[][] data) {
            super(labels);
            this.data0 = data[0];
        }

        @Override
        protected void processSubArr(int p, int count, int threadIndex) {
            int[] cardinalities = this.threadCardinalities[threadIndex];
            double[][] sums = this.threadSums[threadIndex];
            double[][] sumsOfSquares = this.threadSumsOfSquares[threadIndex];
            double[] sums0 = sums[0];
            double[] sumsOfSquares0 = sumsOfSquares[0];
            for (int k = p, kMax = k + count; k < kMax; k++) {
                int label = labels[k];
                if (label > 0) {
                    if (label >= cardinalities.length) {
                        cardinalities = ensureCapacityForLabel(cardinalities, label);
                        ensureSeveralArraysCapacityForLabel(sumsOfSquares, label);
                        ensureSeveralArraysCapacityForLabel(sums, label);
                        sums0 = sums[0];
                        sumsOfSquares0 = sumsOfSquares[0];
                    }
                    cardinalities[label]++;
                    final double value0 = data0[k] & 0xFF;
                    sums0[label] += value0;
                    sumsOfSquares0[label] += value0 * value0;
                    // Note: for better performance, skip cardinalities/sums[0]=0
                }
            }
            this.threadCardinalities[threadIndex] = cardinalities;
        }
    }
    /*Repeat.AutoGeneratedStart !! Auto-generated: NOT EDIT !! */
    static class ForShorts extends CardinalitiesAndSumsOfSquaresCalculator1Channels {
        private final short[] data0;

        public ForShorts(int[] labels, short[][] data) {
            super(labels);
            this.data0 = data[0];
        }

        @Override
        protected void processSubArr(int p, int count, int threadIndex) {
            int[] cardinalities = this.threadCardinalities[threadIndex];
            double[][] sums = this.threadSums[threadIndex];
            double[][] sumsOfSquares = this.threadSumsOfSquares[threadIndex];
            double[] sums0 = sums[0];
            double[] sumsOfSquares0 = sumsOfSquares[0];
            for (int k = p, kMax = k + count; k < kMax; k++) {
                int label = labels[k];
                if (label > 0) {
                    if (label >= cardinalities.length) {
                        cardinalities = ensureCapacityForLabel(cardinalities, label);
                        ensureSeveralArraysCapacityForLabel(sumsOfSquares, label);
                        ensureSeveralArraysCapacityForLabel(sums, label);
                        sums0 = sums[0];
                        sumsOfSquares0 = sumsOfSquares[0];
                    }
                    cardinalities[label]++;
                    final double value0 = data0[k] & 0xFFFF;
                    sums0[label] += value0;
                    sumsOfSquares0[label] += value0 * value0;
                    // Note: for better performance, skip cardinalities/sums[0]=0
                }
            }
            this.threadCardinalities[threadIndex] = cardinalities;
        }
    }

    static class ForInts extends CardinalitiesAndSumsOfSquaresCalculator1Channels {
        private final int[] data0;

        public ForInts(int[] labels, int[][] data) {
            super(labels);
            this.data0 = data[0];
        }

        @Override
        protected void processSubArr(int p, int count, int threadIndex) {
            int[] cardinalities = this.threadCardinalities[threadIndex];
            double[][] sums = this.threadSums[threadIndex];
            double[][] sumsOfSquares = this.threadSumsOfSquares[threadIndex];
            double[] sums0 = sums[0];
            double[] sumsOfSquares0 = sumsOfSquares[0];
            for (int k = p, kMax = k + count; k < kMax; k++) {
                int label = labels[k];
                if (label > 0) {
                    if (label >= cardinalities.length) {
                        cardinalities = ensureCapacityForLabel(cardinalities, label);
                        ensureSeveralArraysCapacityForLabel(sumsOfSquares, label);
                        ensureSeveralArraysCapacityForLabel(sums, label);
                        sums0 = sums[0];
                        sumsOfSquares0 = sumsOfSquares[0];
                    }
                    cardinalities[label]++;
                    final double value0 = data0[k];
                    sums0[label] += value0;
                    sumsOfSquares0[label] += value0 * value0;
                    // Note: for better performance, skip cardinalities/sums[0]=0
                }
            }
            this.threadCardinalities[threadIndex] = cardinalities;
        }
    }

    static class ForFloats extends CardinalitiesAndSumsOfSquaresCalculator1Channels {
        private final float[] data0;

        public ForFloats(int[] labels, float[][] data) {
            super(labels);
            this.data0 = data[0];
        }

        @Override
        protected void processSubArr(int p, int count, int threadIndex) {
            int[] cardinalities = this.threadCardinalities[threadIndex];
            double[][] sums = this.threadSums[threadIndex];
            double[][] sumsOfSquares = this.threadSumsOfSquares[threadIndex];
            double[] sums0 = sums[0];
            double[] sumsOfSquares0 = sumsOfSquares[0];
            for (int k = p, kMax = k + count; k < kMax; k++) {
                int label = labels[k];
                if (label > 0) {
                    if (label >= cardinalities.length) {
                        cardinalities = ensureCapacityForLabel(cardinalities, label);
                        ensureSeveralArraysCapacityForLabel(sumsOfSquares, label);
                        ensureSeveralArraysCapacityForLabel(sums, label);
                        sums0 = sums[0];
                        sumsOfSquares0 = sumsOfSquares[0];
                    }
                    cardinalities[label]++;
                    final double value0 = data0[k];
                    sums0[label] += value0;
                    sumsOfSquares0[label] += value0 * value0;
                    // Note: for better performance, skip cardinalities/sums[0]=0
                }
            }
            this.threadCardinalities[threadIndex] = cardinalities;
        }
    }

    static class ForDoubles extends CardinalitiesAndSumsOfSquaresCalculator1Channels {
        private final double[] data0;

        public ForDoubles(int[] labels, double[][] data) {
            super(labels);
            this.data0 = data[0];
        }

        @Override
        protected void processSubArr(int p, int count, int threadIndex) {
            int[] cardinalities = this.threadCardinalities[threadIndex];
            double[][] sums = this.threadSums[threadIndex];
            double[][] sumsOfSquares = this.threadSumsOfSquares[threadIndex];
            double[] sums0 = sums[0];
            double[] sumsOfSquares0 = sumsOfSquares[0];
            for (int k = p, kMax = k + count; k < kMax; k++) {
                int label = labels[k];
                if (label > 0) {
                    if (label >= cardinalities.length) {
                        cardinalities = ensureCapacityForLabel(cardinalities, label);
                        ensureSeveralArraysCapacityForLabel(sumsOfSquares, label);
                        ensureSeveralArraysCapacityForLabel(sums, label);
                        sums0 = sums[0];
                        sumsOfSquares0 = sumsOfSquares[0];
                    }
                    cardinalities[label]++;
                    final double value0 = data0[k];
                    sums0[label] += value0;
                    sumsOfSquares0[label] += value0 * value0;
                    // Note: for better performance, skip cardinalities/sums[0]=0
                }
            }
            this.threadCardinalities[threadIndex] = cardinalities;
        }
    }
    /*Repeat.AutoGeneratedEnd*/
}
