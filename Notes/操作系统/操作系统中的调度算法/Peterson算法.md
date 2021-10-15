```
volatile int flag1 = 0; //主观因素：flag1表示方法1自身是否要求进入临界区      
volatile int flag2 = 0; //主观因素：flag2表示方法2自身是否要求进入临界区      
volatile int turn = 1; //客观因素：turn取1和2分别表示当前临界区针对方法1还是方法2开放     
    
void fun1(){      
  flag1 = 1;//请求   
  turn = 2;      
  while( flag2==1 && turn==2 ){} //等待   
  //Critical Section      
  ... //临界区内      
  flag1 = 0;//归还    
}      
    
void fun2(){      
  flag2 = 1;//请求
  turn = 1;      
  while( flag1==1 && turn==1 ){}//等待   
  //Critical Section      
  ... //临界区内      
  flag2 = 0;  //归还       
}   

```