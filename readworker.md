- worker （https://www.jianshu.com/p/5fd28540d8d4）
  - OneTimeWorkRequest 任务只执行一次
  
  - PeriodicWorkRequest 
    1.PeriodicWorkRequest重复执行任务，直到被取消才停止。首次执行是任务提交后立即执行或者满足所给的 Constraints条件。
    以后执行都会根据所给的时间间隔来执行。注意任务的执行可能会有延时，因为WorkManager会根据OS的电量进行优化。
    2.PeriodicWorkRequest并不是准确的按照24小时来执行，会有一定的时间延迟。因此如果需要准确的间隔时间来执行任务的话不能使用PeriodicWorkRequest。
    
  - Constraints 可以给任务加一些运行的Constraints条件，比如说当设备空闲时或者正在充电或者连接WiFi时执行任务。  
    
- 一个时间轴效果，包括列表不通类型type （https://github.com/mCyp/Orient-Ui）
- Jetpack即学即用 （https://www.jianshu.com/p/66b93df4b7a6）