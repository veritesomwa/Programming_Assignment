class Task {
    private String taskName; // Name of the task
    private int taskId; // ID of the task
    private int taskWage; // Wage for the task

    // Constructor to initialize the task details
    public Task(String taskName, int taskId, int taskWage) {
        this.taskName = taskName;
        this.taskId = taskId;
        this.taskWage = taskWage;
    }

    // Getter method for task name
    public String getTaskName() {
        return taskName;
    }

    // Getter method for task ID
    public int getTaskId() {
        return taskId;
    }

    // Getter method for task wage
    public int getTaskWage() {
        return taskWage;
    }
}

// WorkerThread class that extends the Thread class
class WorkerThread extends Thread {
    private Task task; // Task object to be processed
    private boolean withAdditionalInfo; // Flag to indicate if additional info should be displayed

    // Constructor to initialize the task and additional info flag
    public WorkerThread(Task task, boolean withAdditionalInfo) {
        this.task = task;
        this.withAdditionalInfo = withAdditionalInfo;
    }

    // Override the run method to define the thread's behavior
    @Override
    public void run() {
        try {
            // Pause the thread for 1000ms (1 second) before executing task details
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace(); // Print stack trace if interrupted
        }

        // Display task details based on the flag
        if (withAdditionalInfo) {
            displayTaskDetailsWithInfo(task); // Display with additional info
        } else {
            displayTaskDetails(task); // Display basic info
        }

        // Display the priority of the thread
        System.out.println(Thread.currentThread().getName() + " Priority: " + Thread.currentThread().getPriority());

        // Check and display the status of the thread
        System.out.println(Thread.currentThread().getName() + " Status: " + Thread.currentThread().getState());
    }

    // Method to display basic task details
    public void displayTaskDetails(Task task) {
        System.out.println("Task Name: " + task.getTaskName() + ", Task ID: " + task.getTaskId() + ", Task Wage: " + task.getTaskWage());
    }

    // Method to display task details with additional info
    public void displayTaskDetailsWithInfo(Task task) {
        System.out.println("Task Name: " + task.getTaskName() + ", Task ID: " + task.getTaskId() + ", Task Wage: " + task.getTaskWage());

        // Additional info based on the task wage
        if (task.getTaskWage() > 150) {
            System.out.println("Additional Info: This task has a high wage and should be given special attention.");
        } else {
            System.out.println("Additional Info: This task is straightforward and can be completed quickly.");
        }
    }
}

public class questionTwo {
    public static void main(String[] args) {
        // Create two Task objects
        Task task1 = new Task("Task One", 1, 100);
        Task task2 = new Task("Task Two", 2, 200);

        // Create two WorkerThread objects with different tasks and additional info flag
        WorkerThread worker1 = new WorkerThread(task1, false); // Without additional info
        WorkerThread worker2 = new WorkerThread(task2, true);  // With additional info

        // Set thread priorities
        worker1.setPriority(Thread.MIN_PRIORITY); // Set minimum priority
        worker2.setPriority(Thread.MAX_PRIORITY); // Set maximum priority

        // Set thread names
        worker1.setName("Worker-1");
        worker2.setName("Worker-2");

        // Start the threads
        worker1.start();
        worker2.start();

        // Wait for threads to finish execution
        try {
            worker1.join(); // Wait for worker1 to finish
            worker2.join(); // Wait for worker2 to finish
        } catch (InterruptedException e) {
            e.printStackTrace(); // Print stack trace if interrupted
        }

        // Display final status of each thread
        System.out.println(worker1.getName() + " Final Status: " + worker1.getState());
        System.out.println(worker2.getName() + " Final Status: " + worker2.getState());
    }
}
