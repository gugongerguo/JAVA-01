package java0.conc0303;

import com.sun.deploy.cache.BaseLocalApplicationProperties;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * 一个简单的代码参考：
 */
public class Homework03 {
    
    public static void main1(String[] args) throws InterruptedException {

        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

        int result = sum(); //这是得到的返回值

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }

    public static void main2(String[] args) {
        long start=System.currentTimeMillis();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int result = 0;
                try {
                    result = sum();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("异步计算结果为："+result);
                System.out.println("子线程使用时间："+ (System.currentTimeMillis()-start) + " ms");
            }
        };
        Thread thread = new Thread(runnable);
        thread.setName("test-thread-1");
        thread.setDaemon(false);
        thread.start();
        System.out.println("main线程使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    public static void main3(String[] args) {
        long start=System.currentTimeMillis();
        Thread thread = new Thread(() -> {
            int result = 0;
            try {
                result = sum();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("异步计算结果为："+result);
            System.out.println("子线程使用时间："+ (System.currentTimeMillis()-start) + " ms");
        });
        thread.setName("test-thread-1");
        thread.setDaemon(false);
        thread.start();
        System.out.println("main线程使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    public static void main4(String[] args) throws InterruptedException, ExecutionException {
        long start=System.currentTimeMillis();
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                int result = sum();
                return "异步计算结果为：" + result + "\n子线程线程使用时间：" + (System.currentTimeMillis() - start) + " ms";
            }
        };
        FutureTask<String> futureTask = new FutureTask<>(callable);
        Thread thread = new Thread(futureTask);
        thread.setName("test-thread-1");
        thread.setDaemon(false);
        thread.start();
        System.out.println(futureTask.get());
        System.out.println("main线程使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    public static void main5(String[] args) throws InterruptedException, ExecutionException {
        long start=System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Thread thread = new Thread(() -> {
            int result = 0;
            try {
                result = sum();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("异步计算结果为：" + result);
            System.out.println("子线程使用时间：" + (System.currentTimeMillis() - start) + " ms");
        });
        executorService.execute(thread);
        executorService.shutdown();
        System.out.println("main线程使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    public static void main6(String[] args) throws InterruptedException, ExecutionException {
        long start=System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int result = 0;
                try {
                    result = sum();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("异步计算结果为："+result);
                System.out.println("子线程使用时间："+ (System.currentTimeMillis()-start) + " ms");
            }
        };
        executorService.execute(runnable);
        executorService.shutdown();
        System.out.println("main线程使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    public static void main7(String[] args) throws InterruptedException, ExecutionException {
        long start=System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                int result = sum();
                return "异步计算结果为：" + result + "\n子线程线程使用时间：" + (System.currentTimeMillis() - start) + " ms";
            }
        };
        Future<String> result = executorService.submit(callable);
        executorService.shutdown();
        System.out.println(result.get());
        System.out.println("main线程使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    public static void main8(String[] args) throws InterruptedException, ExecutionException {
        long start=System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int result = 0;
                try {
                    result = sum();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("异步计算结果为："+result);
                System.out.println("子线程使用时间："+ (System.currentTimeMillis()-start) + " ms");
            }
        };
        Future<?> result = executorService.submit(runnable);
        executorService.shutdown();
        System.out.println(result.get());
        System.out.println("main线程使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    public static void main9(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        long start=System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int result = 0;
                try {
                    result = sum();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("异步计算结果为："+result);
                    System.out.println("子线程使用时间："+ (System.currentTimeMillis()-start) + " ms");
                }
            }
        };
        String info = "子线程1执行结束";
        Future<String> result = executorService.submit(runnable, info);
        executorService.shutdown();
        System.out.println(result.get());
        System.out.println("main线程使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    public static void main10(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        long start=System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ReentrantLock lock = new ReentrantLock(true);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int result = 0;
                lock.lock();
                System.out.println("子线程拿到锁");
                try {
                    result = sum();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    System.out.println("异步计算结果为："+result);
                    System.out.println("子线程使用时间："+ (System.currentTimeMillis()-start) + " ms");
                }
            }
        };
        String info1 = "子线程1执行结束";
        String info2 = "子线程2执行结束";
        Future<String> result1 = executorService.submit(runnable, info1);
        Future<String> result2 = executorService.submit(runnable, info2);
        executorService.shutdown();
        System.out.println(result1.get());
        System.out.println(result2.get());
        System.out.println("main线程使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    public static void main11(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        long start=System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ReentrantLock lock = new ReentrantLock(true);
        Condition point = lock.newCondition();
        Thread thread = new Thread() {
            @Override
            public void run() {
                int result = 0;
                try {
                    System.out.println("子线程已暂停");
                    LockSupport.park();
                    result = sum();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("异步计算结果为："+result);
                    System.out.println("子线程使用时间："+ (System.currentTimeMillis()-start) + " ms");
                }
            }
        };
        thread.start();
        Thread.sleep(3000);
        System.out.println("恢复线程");
        LockSupport.unpark(thread);
        System.out.println("main线程使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    public static void main12(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        long start=System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Semaphore semaphore = new Semaphore(1);
        Thread thread = new Thread() {
            @Override
            public void run() {
                int result = 0;
                try {
                    semaphore.acquire();
                    System.out.println("子线程已占用");
                    result = sum();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("异步计算结果为："+result);
                    System.out.println("子线程使用时间："+ (System.currentTimeMillis()-start) + " ms");
                    System.out.println("子线程已释放");
                    semaphore.release();
                }
            }
        };
        executorService.execute(thread);
        executorService.execute(thread);
        executorService.shutdown();
        Thread.sleep(3000);
        System.out.println("main线程使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    public static void main13(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        long start=System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        for(int i=0;i<2;i++){
            Thread thread = new Thread() {
                private CountDownLatch latch;
                @Override
                public void run() {
                    this.latch = countDownLatch;
                    int result = 0;
                    try {
                        result = sum();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println(Thread.currentThread().getName());
                        System.out.println("异步计算结果为："+result);
                        System.out.println("子线程使用时间："+ (System.currentTimeMillis()-start) + " ms");
                        latch.countDown();
                    }
                }
            };
            thread.start();
        }
        countDownLatch.await();
        System.out.println("main线程使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    public static void main14(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        long start=System.currentTimeMillis();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        for(int i=0;i<2;i++){
            Thread thread = new Thread() {
                private CyclicBarrier cyc;
                @Override
                public void run() {
                    this.cyc = cyclicBarrier;
                    int result = 0;
                    try {
                        result = sum();
                        cyc.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println(Thread.currentThread().getName());
                        System.out.println("异步计算结果为："+result);
                        System.out.println("子线程使用时间："+ (System.currentTimeMillis()-start) + " ms");
                    }
                }
            };
            thread.start();
        }
        System.out.println("main线程使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }
    
    private static int sum() throws InterruptedException {
        Thread.sleep(1000);
        return fibo(36);
    }
    
    private static int fibo(int a) {
        if ( a < 2) 
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
