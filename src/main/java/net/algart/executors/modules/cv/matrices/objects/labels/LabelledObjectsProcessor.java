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

package net.algart.executors.modules.cv.matrices.objects.labels;

import net.algart.arrays.IntArray;

import java.util.Objects;

public abstract class LabelledObjectsProcessor extends LabelsProcessor {
    final int numberOfChannels;
    final int[] lists;
    final int[] listHeads;
    final SingleObjectProcessor processor;
    final int[] cardinalities;

    LabelledObjectsProcessor(
            int[] lists,
            int[] listHeads,
            SingleObjectProcessor processor,
            int numberOfChannels) {
        super(
                IntArray.as(Objects.requireNonNull(listHeads, "Null listHeads")),
                numberOrParticlesInBlockForParallelProcessing(listHeads.length));
        this.numberOfChannels = numberOfChannels;
        this.lists = Objects.requireNonNull(lists, "Null lists");
        this.listHeads = listHeads;
        this.processor = Objects.requireNonNull(processor, "Null processor");
        this.cardinalities = new int[Math.max(0, listHeads.length - 1)];
    }

    public static LabelledObjectsProcessor getInstance(
            int[] lists,
            int[] listHeads,
            SingleObjectProcessor processor,
            Object[] channels) {
        Objects.requireNonNull(lists, "Null lists");
        Objects.requireNonNull(listHeads, "Null listHeads");
        Objects.requireNonNull(processor, "Null processor");
        Objects.requireNonNull(channels, "Null channels");
        if (channels.length == 0) {
            throw new IllegalArgumentException("Empty channels array");
        }
        final Object channel0 = channels[0];
        if (!isArraySupported(channel0)) {
            throw new IllegalArgumentException("Illegal array type: " + channel0);
        }
        for (int k = 1; k < channels.length; k++) {
            if (channels[k].getClass() != channel0.getClass()) {
                throw new IllegalArgumentException("Different type of channels: " + channels[k].getClass()
                        + " != " + channel0.getClass());
            }
        }
        switch (channels.length) {
            /*Repeat() case 1  ==> case 2,,case 3,,case 4,,case 5,,case 6,,case 7;;
                       1(Channels) ==> 2$1,,3$1,,4$1,,5$1,,6$1,,7$1
             */
            case 1: {
                if (channel0 instanceof byte[]) {
                    return new LabelledObjectsProcessor1Channels.ForBytes(
                            lists, listHeads, processor, castToByte(channels));
                } else if (channel0 instanceof short[]) {
                    return new LabelledObjectsProcessor1Channels.ForShorts(
                            lists, listHeads, processor, castToShort(channels));
                } else if (channel0 instanceof int[]) {
                    return new LabelledObjectsProcessor1Channels.ForInts(
                            lists, listHeads, processor, castToInt(channels));
                } else if (channel0 instanceof float[]) {
                    return new LabelledObjectsProcessor1Channels.ForFloats(
                            lists, listHeads, processor, castToFloat(channels));
                } else if (channel0 instanceof double[]) {
                    return new LabelledObjectsProcessor1Channels.ForDoubles(
                            lists, listHeads, processor, castToDouble(channels));
                } else {
                    throw new AssertionError();
                }
            }
            /*Repeat.AutoGeneratedStart !! Auto-generated: NOT EDIT !! */
            case 2: {
                if (channel0 instanceof byte[]) {
                    return new LabelledObjectsProcessor2Channels.ForBytes(
                            lists, listHeads, processor, castToByte(channels));
                } else if (channel0 instanceof short[]) {
                    return new LabelledObjectsProcessor2Channels.ForShorts(
                            lists, listHeads, processor, castToShort(channels));
                } else if (channel0 instanceof int[]) {
                    return new LabelledObjectsProcessor2Channels.ForInts(
                            lists, listHeads, processor, castToInt(channels));
                } else if (channel0 instanceof float[]) {
                    return new LabelledObjectsProcessor2Channels.ForFloats(
                            lists, listHeads, processor, castToFloat(channels));
                } else if (channel0 instanceof double[]) {
                    return new LabelledObjectsProcessor2Channels.ForDoubles(
                            lists, listHeads, processor, castToDouble(channels));
                } else {
                    throw new AssertionError();
                }
            }
            case 3: {
                if (channel0 instanceof byte[]) {
                    return new LabelledObjectsProcessor3Channels.ForBytes(
                            lists, listHeads, processor, castToByte(channels));
                } else if (channel0 instanceof short[]) {
                    return new LabelledObjectsProcessor3Channels.ForShorts(
                            lists, listHeads, processor, castToShort(channels));
                } else if (channel0 instanceof int[]) {
                    return new LabelledObjectsProcessor3Channels.ForInts(
                            lists, listHeads, processor, castToInt(channels));
                } else if (channel0 instanceof float[]) {
                    return new LabelledObjectsProcessor3Channels.ForFloats(
                            lists, listHeads, processor, castToFloat(channels));
                } else if (channel0 instanceof double[]) {
                    return new LabelledObjectsProcessor3Channels.ForDoubles(
                            lists, listHeads, processor, castToDouble(channels));
                } else {
                    throw new AssertionError();
                }
            }
            case 4: {
                if (channel0 instanceof byte[]) {
                    return new LabelledObjectsProcessor4Channels.ForBytes(
                            lists, listHeads, processor, castToByte(channels));
                } else if (channel0 instanceof short[]) {
                    return new LabelledObjectsProcessor4Channels.ForShorts(
                            lists, listHeads, processor, castToShort(channels));
                } else if (channel0 instanceof int[]) {
                    return new LabelledObjectsProcessor4Channels.ForInts(
                            lists, listHeads, processor, castToInt(channels));
                } else if (channel0 instanceof float[]) {
                    return new LabelledObjectsProcessor4Channels.ForFloats(
                            lists, listHeads, processor, castToFloat(channels));
                } else if (channel0 instanceof double[]) {
                    return new LabelledObjectsProcessor4Channels.ForDoubles(
                            lists, listHeads, processor, castToDouble(channels));
                } else {
                    throw new AssertionError();
                }
            }
            case 5: {
                if (channel0 instanceof byte[]) {
                    return new LabelledObjectsProcessor5Channels.ForBytes(
                            lists, listHeads, processor, castToByte(channels));
                } else if (channel0 instanceof short[]) {
                    return new LabelledObjectsProcessor5Channels.ForShorts(
                            lists, listHeads, processor, castToShort(channels));
                } else if (channel0 instanceof int[]) {
                    return new LabelledObjectsProcessor5Channels.ForInts(
                            lists, listHeads, processor, castToInt(channels));
                } else if (channel0 instanceof float[]) {
                    return new LabelledObjectsProcessor5Channels.ForFloats(
                            lists, listHeads, processor, castToFloat(channels));
                } else if (channel0 instanceof double[]) {
                    return new LabelledObjectsProcessor5Channels.ForDoubles(
                            lists, listHeads, processor, castToDouble(channels));
                } else {
                    throw new AssertionError();
                }
            }
            case 6: {
                if (channel0 instanceof byte[]) {
                    return new LabelledObjectsProcessor6Channels.ForBytes(
                            lists, listHeads, processor, castToByte(channels));
                } else if (channel0 instanceof short[]) {
                    return new LabelledObjectsProcessor6Channels.ForShorts(
                            lists, listHeads, processor, castToShort(channels));
                } else if (channel0 instanceof int[]) {
                    return new LabelledObjectsProcessor6Channels.ForInts(
                            lists, listHeads, processor, castToInt(channels));
                } else if (channel0 instanceof float[]) {
                    return new LabelledObjectsProcessor6Channels.ForFloats(
                            lists, listHeads, processor, castToFloat(channels));
                } else if (channel0 instanceof double[]) {
                    return new LabelledObjectsProcessor6Channels.ForDoubles(
                            lists, listHeads, processor, castToDouble(channels));
                } else {
                    throw new AssertionError();
                }
            }
            case 7: {
                if (channel0 instanceof byte[]) {
                    return new LabelledObjectsProcessor7Channels.ForBytes(
                            lists, listHeads, processor, castToByte(channels));
                } else if (channel0 instanceof short[]) {
                    return new LabelledObjectsProcessor7Channels.ForShorts(
                            lists, listHeads, processor, castToShort(channels));
                } else if (channel0 instanceof int[]) {
                    return new LabelledObjectsProcessor7Channels.ForInts(
                            lists, listHeads, processor, castToInt(channels));
                } else if (channel0 instanceof float[]) {
                    return new LabelledObjectsProcessor7Channels.ForFloats(
                            lists, listHeads, processor, castToFloat(channels));
                } else if (channel0 instanceof double[]) {
                    return new LabelledObjectsProcessor7Channels.ForDoubles(
                            lists, listHeads, processor, castToDouble(channels));
                } else {
                    throw new AssertionError();
                }
            }
            /*Repeat.AutoGeneratedEnd*/
            default: {
                if (channel0 instanceof byte[]) {
                    return new ForBytes(lists, listHeads, processor, castToByte(channels));
                } else if (channel0 instanceof short[]) {
                    return new ForShorts(lists, listHeads, processor, castToShort(channels));
                } else if (channel0 instanceof int[]) {
                    return new ForInts(lists, listHeads, processor, castToInt(channels));
                } else if (channel0 instanceof float[]) {
                    return new ForFloats(lists, listHeads, processor, castToFloat(channels));
                } else if (channel0 instanceof double[]) {
                    return new ForDoubles(lists, listHeads, processor, castToDouble(channels));
                } else {
                    throw new AssertionError();
                }
            }
        }
    }

    @Override
    public void close() {
        // nothing to do: we override it for removing declatation of exceptions
    }

    public int[] cardinalities() {
        return cardinalities;
    }

    /*Repeat() Byte ==> Short,,Int,,Float,,Double;;
               byte ==> short,,int,,float,,double */

    private static class ForBytes extends LabelledObjectsProcessor {
        private final byte[][] data;
        private final byte[][][] threadObjectData;
        private final byte[][][] requestedObjectData;

        ForBytes(int[] lists, int[] listHeads, SingleObjectProcessor processor, byte[][] data) {
            super(lists, listHeads, processor, data.length);
            this.data = data;
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
            byte[][] objectData = this.threadObjectData[threadIndex];
            int capacity = objectData[0].length;
            for (int label = p, labelMax = label + count; label < labelMax; label++) {
                int index = listHeads[label];
                int pixelCount = 0;
                while (index != -1) {
                    if (pixelCount >= capacity) {
                        ensureCapacityForPixels(objectData, lists.length, pixelCount);
                        capacity = objectData[0].length;
                    }
                    for (int c = 0; c < numberOfChannels; c++) {
                        objectData[c][pixelCount] = data[c][index];
                    }
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

    private static class ForShorts extends LabelledObjectsProcessor {
        private final short[][] data;
        private final short[][][] threadObjectData;
        private final short[][][] requestedObjectData;

        ForShorts(int[] lists, int[] listHeads, SingleObjectProcessor processor, short[][] data) {
            super(lists, listHeads, processor, data.length);
            this.data = data;
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
            short[][] objectData = this.threadObjectData[threadIndex];
            int capacity = objectData[0].length;
            for (int label = p, labelMax = label + count; label < labelMax; label++) {
                int index = listHeads[label];
                int pixelCount = 0;
                while (index != -1) {
                    if (pixelCount >= capacity) {
                        ensureCapacityForPixels(objectData, lists.length, pixelCount);
                        capacity = objectData[0].length;
                    }
                    for (int c = 0; c < numberOfChannels; c++) {
                        objectData[c][pixelCount] = data[c][index];
                    }
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

    private static class ForInts extends LabelledObjectsProcessor {
        private final int[][] data;
        private final int[][][] threadObjectData;
        private final int[][][] requestedObjectData;

        ForInts(int[] lists, int[] listHeads, SingleObjectProcessor processor, int[][] data) {
            super(lists, listHeads, processor, data.length);
            this.data = data;
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
            int[][] objectData = this.threadObjectData[threadIndex];
            int capacity = objectData[0].length;
            for (int label = p, labelMax = label + count; label < labelMax; label++) {
                int index = listHeads[label];
                int pixelCount = 0;
                while (index != -1) {
                    if (pixelCount >= capacity) {
                        ensureCapacityForPixels(objectData, lists.length, pixelCount);
                        capacity = objectData[0].length;
                    }
                    for (int c = 0; c < numberOfChannels; c++) {
                        objectData[c][pixelCount] = data[c][index];
                    }
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

    private static class ForFloats extends LabelledObjectsProcessor {
        private final float[][] data;
        private final float[][][] threadObjectData;
        private final float[][][] requestedObjectData;

        ForFloats(int[] lists, int[] listHeads, SingleObjectProcessor processor, float[][] data) {
            super(lists, listHeads, processor, data.length);
            this.data = data;
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
            float[][] objectData = this.threadObjectData[threadIndex];
            int capacity = objectData[0].length;
            for (int label = p, labelMax = label + count; label < labelMax; label++) {
                int index = listHeads[label];
                int pixelCount = 0;
                while (index != -1) {
                    if (pixelCount >= capacity) {
                        ensureCapacityForPixels(objectData, lists.length, pixelCount);
                        capacity = objectData[0].length;
                    }
                    for (int c = 0; c < numberOfChannels; c++) {
                        objectData[c][pixelCount] = data[c][index];
                    }
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

    private static class ForDoubles extends LabelledObjectsProcessor {
        private final double[][] data;
        private final double[][][] threadObjectData;
        private final double[][][] requestedObjectData;

        ForDoubles(int[] lists, int[] listHeads, SingleObjectProcessor processor, double[][] data) {
            super(lists, listHeads, processor, data.length);
            this.data = data;
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
            double[][] objectData = this.threadObjectData[threadIndex];
            int capacity = objectData[0].length;
            for (int label = p, labelMax = label + count; label < labelMax; label++) {
                int index = listHeads[label];
                int pixelCount = 0;
                while (index != -1) {
                    if (pixelCount >= capacity) {
                        ensureCapacityForPixels(objectData, lists.length, pixelCount);
                        capacity = objectData[0].length;
                    }
                    for (int c = 0; c < numberOfChannels; c++) {
                        objectData[c][pixelCount] = data[c][index];
                    }
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
