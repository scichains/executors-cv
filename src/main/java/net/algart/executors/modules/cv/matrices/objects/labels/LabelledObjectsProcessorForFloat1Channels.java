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

abstract class LabelledObjectsProcessorForFloat1Channels extends LabelledObjectsProcessorForFloat {
    LabelledObjectsProcessorForFloat1Channels(int[] lists, int[] listHeads, SingleObjectProcessor processor) {
        super(lists, listHeads, processor, 1);
    }

    /*Repeat() Bytes  ==> Shorts,,Ints,,Floats,,Doubles;;
               byte   ==> short,,int,,float,,double;;
               (data\w*\[index\]) \& 0xFF ==> $1 & 0xFFFF,,$1,,$1,,(float) $1
     */
    static class ForBytes extends LabelledObjectsProcessorForFloat1Channels {
        private final byte[] data;

        public ForBytes(int[] lists, int[] listHeads, SingleObjectProcessor processor, byte[] data) {
            super(lists, listHeads, processor);
            this.data = data;
        }

        @Override
        protected void processSubArr(int p, int count, int threadIndex) {
            float[][] objectData = this.threadObjectData[threadIndex];
            float[] objectData0 = objectData[0];
            for (int label = p, labelMax = label + count; label < labelMax; label++) {
                int index = listHeads[label];
                int pixelCount = 0;
                while (index != -1) {
                    if (pixelCount >= objectData0.length) {
                        ensureCapacityForPixels(objectData, lists.length, pixelCount);
                        objectData0 = objectData[0];
                    }
                    objectData0[pixelCount++] = data[index] & 0xFF;
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
    static class ForShorts extends LabelledObjectsProcessorForFloat1Channels {
        private final short[] data;

        public ForShorts(int[] lists, int[] listHeads, SingleObjectProcessor processor, short[] data) {
            super(lists, listHeads, processor);
            this.data = data;
        }

        @Override
        protected void processSubArr(int p, int count, int threadIndex) {
            float[][] objectData = this.threadObjectData[threadIndex];
            float[] objectData0 = objectData[0];
            for (int label = p, labelMax = label + count; label < labelMax; label++) {
                int index = listHeads[label];
                int pixelCount = 0;
                while (index != -1) {
                    if (pixelCount >= objectData0.length) {
                        ensureCapacityForPixels(objectData, lists.length, pixelCount);
                        objectData0 = objectData[0];
                    }
                    objectData0[pixelCount++] = data[index] & 0xFFFF;
                    index = lists[index];
                }
                if (label > 0) {
                    this.cardinalities[label - 1] = pixelCount;
                }
                this.processor.processPixels(label, objectData, pixelCount, threadIndex);
            }
        }
    }

    static class ForInts extends LabelledObjectsProcessorForFloat1Channels {
        private final int[] data;

        public ForInts(int[] lists, int[] listHeads, SingleObjectProcessor processor, int[] data) {
            super(lists, listHeads, processor);
            this.data = data;
        }

        @Override
        protected void processSubArr(int p, int count, int threadIndex) {
            float[][] objectData = this.threadObjectData[threadIndex];
            float[] objectData0 = objectData[0];
            for (int label = p, labelMax = label + count; label < labelMax; label++) {
                int index = listHeads[label];
                int pixelCount = 0;
                while (index != -1) {
                    if (pixelCount >= objectData0.length) {
                        ensureCapacityForPixels(objectData, lists.length, pixelCount);
                        objectData0 = objectData[0];
                    }
                    objectData0[pixelCount++] = data[index];
                    index = lists[index];
                }
                if (label > 0) {
                    this.cardinalities[label - 1] = pixelCount;
                }
                this.processor.processPixels(label, objectData, pixelCount, threadIndex);
            }
        }
    }

    static class ForFloats extends LabelledObjectsProcessorForFloat1Channels {
        private final float[] data;

        public ForFloats(int[] lists, int[] listHeads, SingleObjectProcessor processor, float[] data) {
            super(lists, listHeads, processor);
            this.data = data;
        }

        @Override
        protected void processSubArr(int p, int count, int threadIndex) {
            float[][] objectData = this.threadObjectData[threadIndex];
            float[] objectData0 = objectData[0];
            for (int label = p, labelMax = label + count; label < labelMax; label++) {
                int index = listHeads[label];
                int pixelCount = 0;
                while (index != -1) {
                    if (pixelCount >= objectData0.length) {
                        ensureCapacityForPixels(objectData, lists.length, pixelCount);
                        objectData0 = objectData[0];
                    }
                    objectData0[pixelCount++] = data[index];
                    index = lists[index];
                }
                if (label > 0) {
                    this.cardinalities[label - 1] = pixelCount;
                }
                this.processor.processPixels(label, objectData, pixelCount, threadIndex);
            }
        }
    }

    static class ForDoubles extends LabelledObjectsProcessorForFloat1Channels {
        private final double[] data;

        public ForDoubles(int[] lists, int[] listHeads, SingleObjectProcessor processor, double[] data) {
            super(lists, listHeads, processor);
            this.data = data;
        }

        @Override
        protected void processSubArr(int p, int count, int threadIndex) {
            float[][] objectData = this.threadObjectData[threadIndex];
            float[] objectData0 = objectData[0];
            for (int label = p, labelMax = label + count; label < labelMax; label++) {
                int index = listHeads[label];
                int pixelCount = 0;
                while (index != -1) {
                    if (pixelCount >= objectData0.length) {
                        ensureCapacityForPixels(objectData, lists.length, pixelCount);
                        objectData0 = objectData[0];
                    }
                    objectData0[pixelCount++] = (float) data[index];
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
