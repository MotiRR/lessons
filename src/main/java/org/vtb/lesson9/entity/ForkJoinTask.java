package org.vtb.lesson9.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

public class ForkJoinTask extends RecursiveTask<Integer> {
    private int[] data;
    private int board;

    public ForkJoinTask(int[] data, int board) {
        this.data = data;
        this.board = board;
    }

    protected Integer compute() {
        if (this.data.length > board) {
            List<ForkJoinTask> subtasks = createSubtasks();
            for (ForkJoinTask subtask : subtasks) {
                subtask.fork();
            }
            int result = 0, max = 0;
            for (ForkJoinTask subtask : subtasks) {
                max = subtask.join();
                if (result < max)
                    result = max;
            }
            return result;
        } else {
            return IntStream.of(data).max().orElse(0);
        }
    }

    private List<ForkJoinTask> createSubtasks() {
        return new ArrayList<>(Arrays.asList(
                new ForkJoinTask(Arrays.copyOfRange(data, 0, data.length / 2), board),
                new ForkJoinTask(Arrays.copyOfRange(data, data.length / 2, data.length), board)
        ));
    }

}
