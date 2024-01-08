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

abstract class CardinalitiesAndSumsCalculator4Channels extends CardinalitiesAndSumsCalculator {
    CardinalitiesAndSumsCalculator4Channels(int[] labels) {
        super(labels, 4);
    }

    /*Repeat() Bytes  ==> Shorts,,Ints,,Floats,,Doubles;;
               byte   ==> short,,int,,float,,double;;
               (data\w*\[k\]) \& 0xFF ==> $1 & 0xFFFF,,$1,,$1,,$1
     */
    static class ForBytes extends CardinalitiesAndSumsCalculator4Channels {
        private final byte[] data0;
        private final byte[] data1;
        private final byte[] data2;
        private final byte[] data3;

        public ForBytes(int[] labels, byte[][] data) {
            super(labels);
            this.data0 = data[0];
            this.data1 = data[1];
            this.data2 = data[2];
            this.data3 = data[3];
        }

        @Override
        protected void processSubArr(int p, int count, int threadIndex) {
            int[] cardinalities = this.threadCardinalities[threadIndex];
            double[][] sums = this.threadSums[threadIndex];
            double[] sums0 = sums[0];
            double[] sums1 = sums[1];
            double[] sums2 = sums[2];
            double[] sums3 = sums[3];
            for (int k = p, kMax = k + count; k < kMax; k++) {
                int label = labels[k];
                if (label > 0) {
                    if (label >= cardinalities.length) {
                        cardinalities = ensureCapacityForLabel(cardinalities, label);
                        ensureSeveralArraysCapacityForLabel(sums, label);
                        sums0 = sums[0];
                        sums1 = sums[1];
                        sums2 = sums[2];
                        sums3 = sums[3];
                    }
                    cardinalities[label]++;
                    sums0[label] += data0[k] & 0xFF;
                    sums1[label] += data1[k] & 0xFF;
                    sums2[label] += data2[k] & 0xFF;
                    sums3[label] += data3[k] & 0xFF;
                    // Note: for better performance, skip cardinalities/sums[0]=0
                }
            }
            this.threadCardinalities[threadIndex] = cardinalities;
        }
    }
    /*Repeat.AutoGeneratedStart !! Auto-generated: NOT EDIT !! */
    static class ForShorts extends CardinalitiesAndSumsCalculator4Channels {
        private final short[] data0;
        private final short[] data1;
        private final short[] data2;
        private final short[] data3;

        public ForShorts(int[] labels, short[][] data) {
            super(labels);
            this.data0 = data[0];
            this.data1 = data[1];
            this.data2 = data[2];
            this.data3 = data[3];
        }

        @Override
        protected void processSubArr(int p, int count, int threadIndex) {
            int[] cardinalities = this.threadCardinalities[threadIndex];
            double[][] sums = this.threadSums[threadIndex];
            double[] sums0 = sums[0];
            double[] sums1 = sums[1];
            double[] sums2 = sums[2];
            double[] sums3 = sums[3];
            for (int k = p, kMax = k + count; k < kMax; k++) {
                int label = labels[k];
                if (label > 0) {
                    if (label >= cardinalities.length) {
                        cardinalities = ensureCapacityForLabel(cardinalities, label);
                        ensureSeveralArraysCapacityForLabel(sums, label);
                        sums0 = sums[0];
                        sums1 = sums[1];
                        sums2 = sums[2];
                        sums3 = sums[3];
                    }
                    cardinalities[label]++;
                    sums0[label] += data0[k] & 0xFFFF;
                    sums1[label] += data1[k] & 0xFFFF;
                    sums2[label] += data2[k] & 0xFFFF;
                    sums3[label] += data3[k] & 0xFFFF;
                    // Note: for better performance, skip cardinalities/sums[0]=0
                }
            }
            this.threadCardinalities[threadIndex] = cardinalities;
        }
    }

    static class ForInts extends CardinalitiesAndSumsCalculator4Channels {
        private final int[] data0;
        private final int[] data1;
        private final int[] data2;
        private final int[] data3;

        public ForInts(int[] labels, int[][] data) {
            super(labels);
            this.data0 = data[0];
            this.data1 = data[1];
            this.data2 = data[2];
            this.data3 = data[3];
        }

        @Override
        protected void processSubArr(int p, int count, int threadIndex) {
            int[] cardinalities = this.threadCardinalities[threadIndex];
            double[][] sums = this.threadSums[threadIndex];
            double[] sums0 = sums[0];
            double[] sums1 = sums[1];
            double[] sums2 = sums[2];
            double[] sums3 = sums[3];
            for (int k = p, kMax = k + count; k < kMax; k++) {
                int label = labels[k];
                if (label > 0) {
                    if (label >= cardinalities.length) {
                        cardinalities = ensureCapacityForLabel(cardinalities, label);
                        ensureSeveralArraysCapacityForLabel(sums, label);
                        sums0 = sums[0];
                        sums1 = sums[1];
                        sums2 = sums[2];
                        sums3 = sums[3];
                    }
                    cardinalities[label]++;
                    sums0[label] += data0[k];
                    sums1[label] += data1[k];
                    sums2[label] += data2[k];
                    sums3[label] += data3[k];
                    // Note: for better performance, skip cardinalities/sums[0]=0
                }
            }
            this.threadCardinalities[threadIndex] = cardinalities;
        }
    }

    static class ForFloats extends CardinalitiesAndSumsCalculator4Channels {
        private final float[] data0;
        private final float[] data1;
        private final float[] data2;
        private final float[] data3;

        public ForFloats(int[] labels, float[][] data) {
            super(labels);
            this.data0 = data[0];
            this.data1 = data[1];
            this.data2 = data[2];
            this.data3 = data[3];
        }

        @Override
        protected void processSubArr(int p, int count, int threadIndex) {
            int[] cardinalities = this.threadCardinalities[threadIndex];
            double[][] sums = this.threadSums[threadIndex];
            double[] sums0 = sums[0];
            double[] sums1 = sums[1];
            double[] sums2 = sums[2];
            double[] sums3 = sums[3];
            for (int k = p, kMax = k + count; k < kMax; k++) {
                int label = labels[k];
                if (label > 0) {
                    if (label >= cardinalities.length) {
                        cardinalities = ensureCapacityForLabel(cardinalities, label);
                        ensureSeveralArraysCapacityForLabel(sums, label);
                        sums0 = sums[0];
                        sums1 = sums[1];
                        sums2 = sums[2];
                        sums3 = sums[3];
                    }
                    cardinalities[label]++;
                    sums0[label] += data0[k];
                    sums1[label] += data1[k];
                    sums2[label] += data2[k];
                    sums3[label] += data3[k];
                    // Note: for better performance, skip cardinalities/sums[0]=0
                }
            }
            this.threadCardinalities[threadIndex] = cardinalities;
        }
    }

    static class ForDoubles extends CardinalitiesAndSumsCalculator4Channels {
        private final double[] data0;
        private final double[] data1;
        private final double[] data2;
        private final double[] data3;

        public ForDoubles(int[] labels, double[][] data) {
            super(labels);
            this.data0 = data[0];
            this.data1 = data[1];
            this.data2 = data[2];
            this.data3 = data[3];
        }

        @Override
        protected void processSubArr(int p, int count, int threadIndex) {
            int[] cardinalities = this.threadCardinalities[threadIndex];
            double[][] sums = this.threadSums[threadIndex];
            double[] sums0 = sums[0];
            double[] sums1 = sums[1];
            double[] sums2 = sums[2];
            double[] sums3 = sums[3];
            for (int k = p, kMax = k + count; k < kMax; k++) {
                int label = labels[k];
                if (label > 0) {
                    if (label >= cardinalities.length) {
                        cardinalities = ensureCapacityForLabel(cardinalities, label);
                        ensureSeveralArraysCapacityForLabel(sums, label);
                        sums0 = sums[0];
                        sums1 = sums[1];
                        sums2 = sums[2];
                        sums3 = sums[3];
                    }
                    cardinalities[label]++;
                    sums0[label] += data0[k];
                    sums1[label] += data1[k];
                    sums2[label] += data2[k];
                    sums3[label] += data3[k];
                    // Note: for better performance, skip cardinalities/sums[0]=0
                }
            }
            this.threadCardinalities[threadIndex] = cardinalities;
        }
    }
    /*Repeat.AutoGeneratedEnd*/
}
