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

abstract class FirstNonZeroCalculator1Channels extends FirstNonZeroCalculator {
    FirstNonZeroCalculator1Channels(int[] labels) {
        super(labels, 1);
    }

    /*Repeat() Bytes  ==> Shorts,,Ints,,Floats,,Doubles;;
               byte   ==> short,,int,,float,,double;;
               (data\w+\[\w*\]) \& 0xFF ==> $1 & 0xFFFF,,$1,,$1,,(float) $1;;
               (firstNonZeroIntValues) ==> $1,,$1,,firstNonZeroFloatValues,,...;;
               (new int\[) ==> $1,,$1,,new float[,,...
     */
    static class ForBytes extends FirstNonZeroCalculator1Channels {
        private final byte[] data0;

        public ForBytes(int[] labels, byte[][] data) {
            super(labels);
            this.data0 = data[0];
        }

        @Override
        protected void processSubArr(int p, int count, int threadIndex) {
            int[] cardinalities = this.threadCardinalities[threadIndex];
            int[] firstNonZeroIndexesIncreased = this.threadFirstNonZeroIndexesIncreased[threadIndex];
            for (int k = p, kMax = k + count; k < kMax; k++) {
                int label = labels[k];
                if (label > 0) {
                    if (label >= cardinalities.length) {
                        cardinalities = ensureCapacityForLabel(cardinalities, label);
                        firstNonZeroIndexesIncreased = ensureCapacityForLabel(firstNonZeroIndexesIncreased, label);
                    }
                    cardinalities[label]++;
                    if (firstNonZeroIndexesIncreased[label] == 0) {
                        if (data0[k] != 0) {
                            firstNonZeroIndexesIncreased[label] = k + 1;
                            // - firstNonZeroIndexesIncreased must contain NON-ZERO values to avoid extra time and
                            // efforts for initializing newly allocated arrays by -1 or something like this
                        }
                    }
                }
            }
            this.threadCardinalities[threadIndex] = cardinalities;
            this.threadFirstNonZeroIndexesIncreased[threadIndex] = firstNonZeroIndexesIncreased;
        }

        @Override
        protected void finish() {
            super.finish();
            this.firstNonZeroIntValues = new int[firstNonZeroIndexes.length * numberOfChannels];
            // - zero-filled by Java
            for (int label = 0, disp = 0; label < firstNonZeroIndexes.length; label++, disp += numberOfChannels) {
                final int index = firstNonZeroIndexes[label];
                if (index >= 0) {
                    this.firstNonZeroIntValues[disp] = data0[index] & 0xFF;
                }
            }
        }
    }

    /*Repeat.AutoGeneratedStart !! Auto-generated: NOT EDIT !! */
    static class ForShorts extends FirstNonZeroCalculator1Channels {
        private final short[] data0;

        public ForShorts(int[] labels, short[][] data) {
            super(labels);
            this.data0 = data[0];
        }

        @Override
        protected void processSubArr(int p, int count, int threadIndex) {
            int[] cardinalities = this.threadCardinalities[threadIndex];
            int[] firstNonZeroIndexesIncreased = this.threadFirstNonZeroIndexesIncreased[threadIndex];
            for (int k = p, kMax = k + count; k < kMax; k++) {
                int label = labels[k];
                if (label > 0) {
                    if (label >= cardinalities.length) {
                        cardinalities = ensureCapacityForLabel(cardinalities, label);
                        firstNonZeroIndexesIncreased = ensureCapacityForLabel(firstNonZeroIndexesIncreased, label);
                    }
                    cardinalities[label]++;
                    if (firstNonZeroIndexesIncreased[label] == 0) {
                        if (data0[k] != 0) {
                            firstNonZeroIndexesIncreased[label] = k + 1;
                            // - firstNonZeroIndexesIncreased must contain NON-ZERO values to avoid extra time and
                            // efforts for initializing newly allocated arrays by -1 or something like this
                        }
                    }
                }
            }
            this.threadCardinalities[threadIndex] = cardinalities;
            this.threadFirstNonZeroIndexesIncreased[threadIndex] = firstNonZeroIndexesIncreased;
        }

        @Override
        protected void finish() {
            super.finish();
            this.firstNonZeroIntValues = new int[firstNonZeroIndexes.length * numberOfChannels];
            // - zero-filled by Java
            for (int label = 0, disp = 0; label < firstNonZeroIndexes.length; label++, disp += numberOfChannels) {
                final int index = firstNonZeroIndexes[label];
                if (index >= 0) {
                    this.firstNonZeroIntValues[disp] = data0[index] & 0xFFFF;
                }
            }
        }
    }


    static class ForInts extends FirstNonZeroCalculator1Channels {
        private final int[] data0;

        public ForInts(int[] labels, int[][] data) {
            super(labels);
            this.data0 = data[0];
        }

        @Override
        protected void processSubArr(int p, int count, int threadIndex) {
            int[] cardinalities = this.threadCardinalities[threadIndex];
            int[] firstNonZeroIndexesIncreased = this.threadFirstNonZeroIndexesIncreased[threadIndex];
            for (int k = p, kMax = k + count; k < kMax; k++) {
                int label = labels[k];
                if (label > 0) {
                    if (label >= cardinalities.length) {
                        cardinalities = ensureCapacityForLabel(cardinalities, label);
                        firstNonZeroIndexesIncreased = ensureCapacityForLabel(firstNonZeroIndexesIncreased, label);
                    }
                    cardinalities[label]++;
                    if (firstNonZeroIndexesIncreased[label] == 0) {
                        if (data0[k] != 0) {
                            firstNonZeroIndexesIncreased[label] = k + 1;
                            // - firstNonZeroIndexesIncreased must contain NON-ZERO values to avoid extra time and
                            // efforts for initializing newly allocated arrays by -1 or something like this
                        }
                    }
                }
            }
            this.threadCardinalities[threadIndex] = cardinalities;
            this.threadFirstNonZeroIndexesIncreased[threadIndex] = firstNonZeroIndexesIncreased;
        }

        @Override
        protected void finish() {
            super.finish();
            this.firstNonZeroIntValues = new int[firstNonZeroIndexes.length * numberOfChannels];
            // - zero-filled by Java
            for (int label = 0, disp = 0; label < firstNonZeroIndexes.length; label++, disp += numberOfChannels) {
                final int index = firstNonZeroIndexes[label];
                if (index >= 0) {
                    this.firstNonZeroIntValues[disp] = data0[index];
                }
            }
        }
    }


    static class ForFloats extends FirstNonZeroCalculator1Channels {
        private final float[] data0;

        public ForFloats(int[] labels, float[][] data) {
            super(labels);
            this.data0 = data[0];
        }

        @Override
        protected void processSubArr(int p, int count, int threadIndex) {
            int[] cardinalities = this.threadCardinalities[threadIndex];
            int[] firstNonZeroIndexesIncreased = this.threadFirstNonZeroIndexesIncreased[threadIndex];
            for (int k = p, kMax = k + count; k < kMax; k++) {
                int label = labels[k];
                if (label > 0) {
                    if (label >= cardinalities.length) {
                        cardinalities = ensureCapacityForLabel(cardinalities, label);
                        firstNonZeroIndexesIncreased = ensureCapacityForLabel(firstNonZeroIndexesIncreased, label);
                    }
                    cardinalities[label]++;
                    if (firstNonZeroIndexesIncreased[label] == 0) {
                        if (data0[k] != 0) {
                            firstNonZeroIndexesIncreased[label] = k + 1;
                            // - firstNonZeroIndexesIncreased must contain NON-ZERO values to avoid extra time and
                            // efforts for initializing newly allocated arrays by -1 or something like this
                        }
                    }
                }
            }
            this.threadCardinalities[threadIndex] = cardinalities;
            this.threadFirstNonZeroIndexesIncreased[threadIndex] = firstNonZeroIndexesIncreased;
        }

        @Override
        protected void finish() {
            super.finish();
            this.firstNonZeroFloatValues = new float[firstNonZeroIndexes.length * numberOfChannels];
            // - zero-filled by Java
            for (int label = 0, disp = 0; label < firstNonZeroIndexes.length; label++, disp += numberOfChannels) {
                final int index = firstNonZeroIndexes[label];
                if (index >= 0) {
                    this.firstNonZeroFloatValues[disp] = data0[index];
                }
            }
        }
    }


    static class ForDoubles extends FirstNonZeroCalculator1Channels {
        private final double[] data0;

        public ForDoubles(int[] labels, double[][] data) {
            super(labels);
            this.data0 = data[0];
        }

        @Override
        protected void processSubArr(int p, int count, int threadIndex) {
            int[] cardinalities = this.threadCardinalities[threadIndex];
            int[] firstNonZeroIndexesIncreased = this.threadFirstNonZeroIndexesIncreased[threadIndex];
            for (int k = p, kMax = k + count; k < kMax; k++) {
                int label = labels[k];
                if (label > 0) {
                    if (label >= cardinalities.length) {
                        cardinalities = ensureCapacityForLabel(cardinalities, label);
                        firstNonZeroIndexesIncreased = ensureCapacityForLabel(firstNonZeroIndexesIncreased, label);
                    }
                    cardinalities[label]++;
                    if (firstNonZeroIndexesIncreased[label] == 0) {
                        if (data0[k] != 0) {
                            firstNonZeroIndexesIncreased[label] = k + 1;
                            // - firstNonZeroIndexesIncreased must contain NON-ZERO values to avoid extra time and
                            // efforts for initializing newly allocated arrays by -1 or something like this
                        }
                    }
                }
            }
            this.threadCardinalities[threadIndex] = cardinalities;
            this.threadFirstNonZeroIndexesIncreased[threadIndex] = firstNonZeroIndexesIncreased;
        }

        @Override
        protected void finish() {
            super.finish();
            this.firstNonZeroFloatValues = new float[firstNonZeroIndexes.length * numberOfChannels];
            // - zero-filled by Java
            for (int label = 0, disp = 0; label < firstNonZeroIndexes.length; label++, disp += numberOfChannels) {
                final int index = firstNonZeroIndexes[label];
                if (index >= 0) {
                    this.firstNonZeroFloatValues[disp] = (float) data0[index];
                }
            }
        }
    }

    /*Repeat.AutoGeneratedEnd*/
}
