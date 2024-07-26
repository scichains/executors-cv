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

abstract class LabelledObjectsProcessor1Channels extends LabelledObjectsProcessor {
    LabelledObjectsProcessor1Channels(int[] lists, int[] listHeads, SingleObjectProcessor processor) {
        super(lists, listHeads, processor, 1);
    }

    /*Repeat() Byte ==> Short,,Int,,Float,,Double;;
               byte ==> short,,int,,float,,double */

    static class ForBytes extends LabelledObjectsProcessor1Channels {
        private final byte[] data0;
        private final byte[][][] threadObjectData;
        private final byte[][][] requestedObjectData;

        public ForBytes(int[] lists, int[] listHeads, SingleObjectProcessor processor, byte[][] data) {
            super(lists, listHeads, processor);
            this.data0 = data[0];
            this.requestedObjectData = requestByteArrays(numberOfTasks(), numberOfChannels);
            this.threadObjectData = new byte[this.requestedObjectData.length][][];
            for (int k = 0; k < this.requestedObjectData.length; k++) {
                this.threadObjectData[k] = requestedObjectData[k].clone();
                // - Java arrays threadObjectData[k][c] will be probably reallocated,
                // and we need to store original references to correctly release them
            }
        }

        @Override
        public void close() {
            releaseByteArrays(requestedObjectData);
        }

        @Override
        protected void processSubArr(int p, int count, int threadIndex) {
            final byte[][] objectData = this.threadObjectData[threadIndex];
            byte[] objectData0 = objectData[0];
            for (int label = p, labelMax = label + count; label < labelMax; label++) {
                int index = listHeads[label];
                int pixelCount = 0;
                while (index != -1) {
                    if (pixelCount >= objectData0.length) {
                        ensureCapacityForPixels(objectData, lists.length, pixelCount);
                        objectData0 = objectData[0];
                    }
                    objectData0[pixelCount] = data0[index];
                    pixelCount++;
                    index = lists[index];
                }
                if (label > 0) {
                    this.cardinalities[label - 1] = pixelCount;
                }
                this.processor.processPixels(label, objectData, pixelCount, threadIndex);
            }
        }
    }
    /*Repeat.AutoGeneratedStart !! Auto-generated: NOT EDIT !! */

    static class ForShorts extends LabelledObjectsProcessor1Channels {
        private final short[] data0;
        private final short[][][] threadObjectData;
        private final short[][][] requestedObjectData;

        public ForShorts(int[] lists, int[] listHeads, SingleObjectProcessor processor, short[][] data) {
            super(lists, listHeads, processor);
            this.data0 = data[0];
            this.requestedObjectData = requestShortArrays(numberOfTasks(), numberOfChannels);
            this.threadObjectData = new short[this.requestedObjectData.length][][];
            for (int k = 0; k < this.requestedObjectData.length; k++) {
                this.threadObjectData[k] = requestedObjectData[k].clone();
                // - Java arrays threadObjectData[k][c] will be probably reallocated,
                // and we need to store original references to correctly release them
            }
        }

        @Override
        public void close() {
            releaseShortArrays(requestedObjectData);
        }

        @Override
        protected void processSubArr(int p, int count, int threadIndex) {
            final short[][] objectData = this.threadObjectData[threadIndex];
            short[] objectData0 = objectData[0];
            for (int label = p, labelMax = label + count; label < labelMax; label++) {
                int index = listHeads[label];
                int pixelCount = 0;
                while (index != -1) {
                    if (pixelCount >= objectData0.length) {
                        ensureCapacityForPixels(objectData, lists.length, pixelCount);
                        objectData0 = objectData[0];
                    }
                    objectData0[pixelCount] = data0[index];
                    pixelCount++;
                    index = lists[index];
                }
                if (label > 0) {
                    this.cardinalities[label - 1] = pixelCount;
                }
                this.processor.processPixels(label, objectData, pixelCount, threadIndex);
            }
        }
    }

    static class ForInts extends LabelledObjectsProcessor1Channels {
        private final int[] data0;
        private final int[][][] threadObjectData;
        private final int[][][] requestedObjectData;

        public ForInts(int[] lists, int[] listHeads, SingleObjectProcessor processor, int[][] data) {
            super(lists, listHeads, processor);
            this.data0 = data[0];
            this.requestedObjectData = requestIntArrays(numberOfTasks(), numberOfChannels);
            this.threadObjectData = new int[this.requestedObjectData.length][][];
            for (int k = 0; k < this.requestedObjectData.length; k++) {
                this.threadObjectData[k] = requestedObjectData[k].clone();
                // - Java arrays threadObjectData[k][c] will be probably reallocated,
                // and we need to store original references to correctly release them
            }
        }

        @Override
        public void close() {
            releaseIntArrays(requestedObjectData);
        }

        @Override
        protected void processSubArr(int p, int count, int threadIndex) {
            final int[][] objectData = this.threadObjectData[threadIndex];
            int[] objectData0 = objectData[0];
            for (int label = p, labelMax = label + count; label < labelMax; label++) {
                int index = listHeads[label];
                int pixelCount = 0;
                while (index != -1) {
                    if (pixelCount >= objectData0.length) {
                        ensureCapacityForPixels(objectData, lists.length, pixelCount);
                        objectData0 = objectData[0];
                    }
                    objectData0[pixelCount] = data0[index];
                    pixelCount++;
                    index = lists[index];
                }
                if (label > 0) {
                    this.cardinalities[label - 1] = pixelCount;
                }
                this.processor.processPixels(label, objectData, pixelCount, threadIndex);
            }
        }
    }

    static class ForFloats extends LabelledObjectsProcessor1Channels {
        private final float[] data0;
        private final float[][][] threadObjectData;
        private final float[][][] requestedObjectData;

        public ForFloats(int[] lists, int[] listHeads, SingleObjectProcessor processor, float[][] data) {
            super(lists, listHeads, processor);
            this.data0 = data[0];
            this.requestedObjectData = requestFloatArrays(numberOfTasks(), numberOfChannels);
            this.threadObjectData = new float[this.requestedObjectData.length][][];
            for (int k = 0; k < this.requestedObjectData.length; k++) {
                this.threadObjectData[k] = requestedObjectData[k].clone();
                // - Java arrays threadObjectData[k][c] will be probably reallocated,
                // and we need to store original references to correctly release them
            }
        }

        @Override
        public void close() {
            releaseFloatArrays(requestedObjectData);
        }

        @Override
        protected void processSubArr(int p, int count, int threadIndex) {
            final float[][] objectData = this.threadObjectData[threadIndex];
            float[] objectData0 = objectData[0];
            for (int label = p, labelMax = label + count; label < labelMax; label++) {
                int index = listHeads[label];
                int pixelCount = 0;
                while (index != -1) {
                    if (pixelCount >= objectData0.length) {
                        ensureCapacityForPixels(objectData, lists.length, pixelCount);
                        objectData0 = objectData[0];
                    }
                    objectData0[pixelCount] = data0[index];
                    pixelCount++;
                    index = lists[index];
                }
                if (label > 0) {
                    this.cardinalities[label - 1] = pixelCount;
                }
                this.processor.processPixels(label, objectData, pixelCount, threadIndex);
            }
        }
    }

    static class ForDoubles extends LabelledObjectsProcessor1Channels {
        private final double[] data0;
        private final double[][][] threadObjectData;
        private final double[][][] requestedObjectData;

        public ForDoubles(int[] lists, int[] listHeads, SingleObjectProcessor processor, double[][] data) {
            super(lists, listHeads, processor);
            this.data0 = data[0];
            this.requestedObjectData = requestDoubleArrays(numberOfTasks(), numberOfChannels);
            this.threadObjectData = new double[this.requestedObjectData.length][][];
            for (int k = 0; k < this.requestedObjectData.length; k++) {
                this.threadObjectData[k] = requestedObjectData[k].clone();
                // - Java arrays threadObjectData[k][c] will be probably reallocated,
                // and we need to store original references to correctly release them
            }
        }

        @Override
        public void close() {
            releaseDoubleArrays(requestedObjectData);
        }

        @Override
        protected void processSubArr(int p, int count, int threadIndex) {
            final double[][] objectData = this.threadObjectData[threadIndex];
            double[] objectData0 = objectData[0];
            for (int label = p, labelMax = label + count; label < labelMax; label++) {
                int index = listHeads[label];
                int pixelCount = 0;
                while (index != -1) {
                    if (pixelCount >= objectData0.length) {
                        ensureCapacityForPixels(objectData, lists.length, pixelCount);
                        objectData0 = objectData[0];
                    }
                    objectData0[pixelCount] = data0[index];
                    pixelCount++;
                    index = lists[index];
                }
                if (label > 0) {
                    this.cardinalities[label - 1] = pixelCount;
                }
                this.processor.processPixels(label, objectData, pixelCount, threadIndex);
            }
        }
    }
    /*Repeat.AutoGeneratedEnd*/
}
