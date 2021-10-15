时间片轮转（RR）
方式：周期性切换，为每一个进程分配一个时间片，时钟中断–>轮换。
难点：时间片选择，太长则退化为FCFS，太短则会因为进程之间的切换浪费CPU时间。
优点：公平，有利于交互式计算，响应时间快
缺点：进程切换导致较高的开销，对于大小不同进程有利，但对于大小相同的进程不如FCFS算法。


        A.轮转法的基本原理:
    
           在轮转法(RR)法中，系统将所有的就绪进程按FCFS策略排成一个就绪队列。系统可设置每隔一定时间(如30ms)便产生一次中断，去激活进程调度程序进行调度，把CPU分配给队首进程，并令其执行一个时间片。当它运行完毕后，又把处理机分配给就绪队列中新的队首进程，也让其执行一个时间片片。这样，就可以保证就绪队列中的所有进程在确定的时间段内，都能获得一个时间片的处理机时间。
    
       B.进程切换时机:
    
          在轮转调度算法中，应在何时进行进程的切换，可分成两种情况:1.若一个时间片尚未用完，正在运行的进程便已经完成，就立即激活调度程序，将它从就绪队列中删除，在调度就绪队列中队首的进程运行，并启动一个新的时间片。2.在一个时间片用完时，计时器中断处理程序被激活。如果进程尚未运行完毕，调度程序将把它送往就绪队列的末尾。
    
       C.时间片大小的确定：
    
          在轮转算法中，时间片的大小对系统性能有着很大的影响。若选择很小的时间内片，将有利于短作业，因为它能在该时间片内完成。但时间片小，意味着会频繁地执行进程调度和进程上下文的切换，这无疑会增加系统的开销。反之，若事件片选择的太长，且为使每个进程都能在一个时间片内完成，轮转算法便退化为FCFS算法，无法满足短作业和交互式用户的需求。一个较为可取的时间片大小是略大于一次典型的交互所需要的时间，使大多数交互式进程能在一个时间片内完成，从而可以获得很小的响应时间。
    
        D.算法流程图:


​                                  

         E.实现代码:



    class PCBRR{
    	public int id;
    	public int everyTimeCount;
        public int alreadyCpuTime;
        public int stillNeedCpuTime;
        
    
    public PCBRR(int id, int everyTimeCount, int alreadyCpuTime, int stillNeedCpuTime) {
    	super();
    	this.id = id;
    	this.everyTimeCount = everyTimeCount;
    	this.alreadyCpuTime = alreadyCpuTime;
    	this.stillNeedCpuTime = stillNeedCpuTime;
    }
    
    @Override
    public String toString() {
    	return "进程号:" + this.id + " 已占有的CPU时间:" + this.alreadyCpuTime + " 还需的CPU时间:"
             + this.stillNeedCpuTime+" 每次轮转的时间片数:"+this.everyTimeCount;
    }
    
    }




```

private static void RoundRobin() {
		LinkedList<PCBRR> pcbs = new LinkedList<PCBRR>();
		System.out.println("请输入进程数目：");
		int num = scanner.nextInt();
		System.out.println("系统为这" + num + "个进程随机分配优先级数和运行所需的CPU数.以下是进程的详细情况:");
		for (int i = 0; i < num; i++) {
			//产生n个进程(id号,每次轮转的时间片数,已占用的CPU时间片数,仍需要的时间片数)
			pcbs.addLast(new PCBRR(i,random.nextInt(5) + 1, 0, random.nextInt(30) + 1));
		}
		Iterator iterator = pcbs.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		System.out.println();System.out.println();
		System.out.println("下面开始进行轮转法进程调度算法---------------");
		LinkedList<Integer> donePCB = new LinkedList<Integer>();
		int current = 1;
		while(pcbs.size()>0)
		{
			System.out.println("当前正在执行第" + (current++) + "次时间片");
			PCBRR pcbrr=pcbs.removeFirst();
			System.out.println("将要执行的进程为 ----"+pcbrr);
			pcbrr.alreadyCpuTime++;
			pcbrr.stillNeedCpuTime--;
			if(pcbrr.stillNeedCpuTime<=0)    //进程总体运行结束
			{
				donePCB.addLast(pcbrr.id);
			}
			else if(pcbrr.alreadyCpuTime>=pcbrr.everyTimeCount)
			{         //进程已经运行完其所分配的每次轮转时间片，将其放在轮转队尾
				pcbrr.alreadyCpuTime=0;
				pcbs.addLast(pcbrr);
			}
	//进程位运行完其所分配的每次轮转时间片，下面仍将继续运行该进程
			else {
				pcbs.addFirst(pcbrr);
			}
			System.out.println("执行完这个时间片后系统轮转队列中的所有进程的情况如下:");
			if(pcbs.size()>0)
			{
				Iterator temp = pcbs.iterator();
				while (temp.hasNext()) {
					System.out.println(temp.next());
				}
			}
			else
				System.out.println("空");
			
			
			System.out.println("已经完成的进程有:(用进程号表示)");
			Iterator itera = donePCB.iterator();
			if (!itera.hasNext()) {
				System.out.println("无");
		       System.out.println();System.out.println();
			   System.out.println();System.out.println();
			   continue;
			}
			
			while (itera.hasNext()) {
				System.out.print(itera.next()+" ");
			}
			System.out.println();System.out.println();
			System.out.println();System.out.println();
		}
		System.out.println("轮转法进程调度算法结束---------------");
	}

```





轮转也称时间片技术（time slicing，SL），对于轮转法，最重要的是时间片的长度。轮转算法以一个周期（q）产生中断，当中断发生时，当前运行的程序置于就绪队列（队尾）中，然后基于FCFS选择下一个就绪作业运行。在这里我们以时间片q=1举例。

q=1，所以一次只能运行一个时间片。

0：A1运转（右标表示运行了几个）

1：A2运转

2：B1运转，A3等待（B开始）

3：A3运转，B2等待

4：B2运转，C1等待，（A结束）

5：C1运转，B3等待（C开始）

6：B3运转，D1等待，C2等待

7：D1运转，C2等待，B4等待（D开始）

8：C2运行，B4等待，E1等待，D2等待

9：B4运行，E1等待，D2等待，C3等待

10：E1运行，D2等待，C3等待，B5等待（E开始）

11：D2运行，C3等待，B5等待，E2等待

12：C3运行，B5等待，E2等待，D3等待

13：B5运行，E2等待，D3等待，C4等待

14：E2运行，D3等待，C4等待，B6等待

15：D3运行，C4等待，B6等待（E结束）

16：C4运行，B6等待，D4等待

17：B6运行，D4等待（C结束）

18：D5运行，D6等待（B结束）

19：D6运行

20：D结束

表格展示：

 ![img](https://img-blog.csdn.net/20180121005505612?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQveGllbWlueWFvMTIz/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

 

