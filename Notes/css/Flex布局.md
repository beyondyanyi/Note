# Flex基本概念

在 flex 容器中默认存在两条轴，水平主轴(main axis) 和垂直的交叉轴(cross axis)，这是默认的设置，当然你可以通过修改使垂直方向变为主轴，水平方向变为交叉轴，这个我们后面再说。

在容器中的每个单元块被称之为 flex item，每个项目占据的主轴空间为 (main size), 占据的交叉轴的空间为 (cross size)。

这里需要强调，不能先入为主认为宽度就是 main size，高度就是 cross size，这个还要取决于你主轴的方向，如果你垂直方向是主轴，那么项目的高度就是 main size。

# Flex容器

首先，实现 flex 布局需要先指定一个容器，任何一个容器都可以被指定为 flex 布局，这样容器内部的元素就可以使用 flex 来进行布局。

```css
.container {
    display: flex | inline-flex;       //可以有两种取值
}
```

分别生成一个块状或行内的 flex 容器盒子。简单说来，如果你使用块元素如 div，你就可以使用 flex，而如果你使用行内元素，你可以使用 inline-flex。

**需要注意的是：当时设置 flex 布局之后，子元素的 float、clear、vertical-align 的属性将会失效。**

有下面六种属性可以设置在容器上，它们分别是：

1. ## flex-direction
   
   决定主轴的方向（即项目的排列方向）
   
   ```css
   .container {
       flex-direction: row | row-reverse | column | column-reverse;
   }
   ```
   
     默认值row：主轴为水平方向，起点在左端。
   
    row-reverse：主轴为水平方向，起点在右端
   
   column：主轴为垂直方向，起点在上沿
   
   column-reverse：主轴为垂直方向，起点在下沿

2. flex-wrap
3. flex-flow
4. justify-content
5. align-items
6. align-content

## **2. flex-wrap: 决定容器内项目是否可换行**

默认情况下，项目都排在主轴线上，使用 flex-wrap 可实现项目的换行。

```css
.container {
    flex-wrap: nowrap | wrap | wrap-reverse;
}
```

默认值：nowrap 不换行，即当主轴尺寸固定时，当空间不足时，项目尺寸会随之调整而并不会挤到下一行。

wrap：项目主轴总尺寸超出容器时换行，第一行在上方

wrap-reverse：换行，第一行在下方

## **3. flex-flow: flex-direction 和 flex-wrap 的简写形式**

```css
.container {
    flex-flow: <flex-direction> || <flex-wrap>;
}
```

默认值为: row nowrap，感觉没什么卵用，老老实实分开写就好了。这样就不用记住这个属性了。

## **4. justify-content：定义了项目在主轴的对齐方式。**

```css
.container {
    justify-content: flex-start | flex-end | center | space-between | space-around;
}
```

**建立在主轴为水平方向时测试，即 flex-direction: row**

默认值: flex-start 左对齐

flex-end：右对齐

center：居中

space-between：两端对齐，项目之间的间隔相等，即剩余空间等分成间隙。

space-around：每个项目两侧的间隔相等，所以项目之间的间隔比项目与边缘的间隔大一倍。

## **5. align-items: 定义了项目在交叉轴上的对齐方式**

```css
.container {
    align-items: flex-start | flex-end | center | baseline | stretch;
}
```

**建立在主轴为水平方向时测试，即 flex-direction: row**

1)默认值为 stretch 即如果项目未设置高度或者设为 auto，将占满整个容器的高度。

假设容器高度设置为 100px，而项目都没有设置高度的情况下，则项目的高度也为 100px。

2)flex-start：交叉轴的起点对齐

假设容器高度设置为 100px，而项目分别为 20px, 40px, 60px, 80px, 100px, 则如上图显示。

3)flex-end：交叉轴的终点对齐

4)center：交叉轴的中点对齐

5)baseline: 项目的第一行文字的基线对齐

## **6. align-content: 定义了多根轴线的对齐方式，如果项目只有一根轴线，那么该属性将不起作用**

```css
.container {
    align-content: flex-start | flex-end | center | space-between | space-around | stretch;
}
```

当你 flex-wrap 设置为 nowrap 的时候，容器仅存在一根轴线，因为项目不会换行，就不会产生多条轴线。

当你 flex-wrap 设置为 wrap 的时候，容器可能会出现多条轴线，这时候你就需要去设置多条轴线之间的对齐方式了。

建立在主轴为水平方向时测试，即 flex-direction: row, flex-wrap: wrap

1)默认值为 stretch，看下面的图就很好理解了

从图可以看出又三条轴线(因为容器宽度有限)，当值为 stretch 时会三条轴线平分容器的垂直方向上的空间。

值得注意的是，虽然在每条轴线上项目的默认值也为 stretch，但是由于我每个项目我都设置了高度，所以它并没有撑开整个容器。如果项目不设置高度的话就会变成下面这样：

2)flex-start：轴线全部在交叉轴上的起点对齐

flex-end：轴线全部在交叉轴上的终点对齐

center：轴线全部在交叉轴上的中间对齐

space-between：轴线两端对齐，之间的间隔相等，即剩余空间等分成间隙。

space-around：每个轴线两侧的间隔相等，所以轴线之间的间隔比轴线与边缘的间隔大一倍。

7. ## Flex 项目属性：

有六种属性可运用在 item 项目上：

1. order
2. flex-basis
3. flex-grow
4. flex-shrink
5. flex
6. align-self

**1. order: 定义项目在容器中的排列顺序，数值越小，排列越靠前，默认值为 0**

```css
.item {
    order: <integer>;
}
```

**2. flex-basis: 定义了在分配多余空间之前，项目占据的主轴空间，浏览器根据这个属性，计算主轴是否有多余空间**

```css
.item {
    flex-basis: <length> | auto;
}
```

默认值：auto，即项目本来的大小, 这时候 item 的宽高取决于 width 或 height 的值。  

**当主轴为水平方向的时候，当设置了 flex-basis，项目的宽度设置值会失效，flex-basis 需要跟 flex-grow 和 flex-shrink 配合使用才能发挥效果。**

- 当 flex-basis 值为 0 % 时，是把该项目视为零尺寸的，故即使声明该尺寸为 140px，也并没有什么用。
- 当 flex-basis 值为 auto 时，则跟根据尺寸的设定值(假如为 100px)，则这 100px 不会纳入剩余空间。

3**. flex-grow: 定义项目的放大比例  
**

```css
.item {
    flex-grow: <number>;
}
```

默认值为 0，即如果存在剩余空间，也不放大

![](https://pic4.zhimg.com/80/v2-5f7898c1f51fa7274a2c0b4a9dfd88c3_720w.webp)

当所有的项目都以 flex-basis 的值进行排列后，仍有剩余空间，那么这时候 flex-grow 就会发挥作用了。

如果所有项目的 flex-grow 属性都为 1，则它们将等分剩余空间。(如果有的话)

如果一个项目的 flex-grow 属性为 2，其他项目都为 1，则前者占据的剩余空间将比其他项多一倍。  

当然如果当所有项目以 flex-basis 的值排列完后发现空间不够了，且 flex-wrap：nowrap 时，此时 flex-grow 则不起作用了，这时候就需要接下来的这个属性。

**4. flex-shrink: 定义了项目的缩小比例**

```css
.item {
    flex-shrink: <number>;
}
```

默认值: 1，即如果空间不足，该项目将缩小，负值对该属性无效。  

![](https://pic4.zhimg.com/80/v2-383e97971a7fc8c4f84e6a85406dbcaf_720w.webp)

这里可以看出，虽然每个项目都设置了宽度为 50px，但是由于自身容器宽度只有 200px，这时候每个项目会被同比例进行缩小，因为默认值为 1。

同理可得：

如果所有项目的 flex-shrink 属性都为 1，当空间不足时，都将等比例缩小。  

如果一个项目的 flex-shrink 属性为 0，其他项目都为 1，则空间不足时，前者不缩小。  

**5. flex: flex-grow, flex-shrink 和 flex-basis的简写**  

```css
.item{
    flex: none | [ <'flex-grow'> <'flex-shrink'>? || <'flex-basis'> ]
} 
```

flex 的默认值是以上三个属性值的组合。假设以上三个属性同样取默认值，则 flex 的默认值是 0 1 auto。

有关快捷值：auto (1 1 auto) 和 none (0 0 auto)  

关于 flex 取值，还有许多特殊的情况，可以按以下来进行划分：

- 当 flex 取值为一个非负数字，则该数字为 flex-grow 值，flex-shrink 取 1，flex-basis 取 0%，如下是等同的：  

```css
.item {flex: 1;}
.item {
    flex-grow: 1;
    flex-shrink: 1;
    flex-basis: 0%;
}
```

- 当 flex 取值为 0 时，对应的三个值分别为 0 1 0%

```css
.item {flex: 0;}
.item {
    flex-grow: 0;
    flex-shrink: 1;
    flex-basis: 0%;
}
```

- 当 flex 取值为一个长度或百分比，则视为 flex-basis 值，flex-grow 取 1，flex-shrink 取 1，有如下等同情况（注意 0% 是一个百分比而不是一个非负数字）  

```css
.item-1 {flex: 0%;}
.item-1 {
    flex-grow: 1;
    flex-shrink: 1;
    flex-basis: 0%;
}

.item-2 {flex: 24px;}
.item-2 {
    flex-grow: 1;
    flex-shrink: 1;
    flex-basis: 24px;
}
```

- 当 flex 取值为两个非负数字，则分别视为 flex-grow 和 flex-shrink 的值，flex-basis 取 0%，如下是等同的：  

```css
.item {flex: 2 3;}
.item {
    flex-grow: 2;
    flex-shrink: 3;
    flex-basis: 0%;
}
```

- 当 flex 取值为一个非负数字和一个长度或百分比，则分别视为 flex-grow 和 flex-basis 的值，flex-shrink 取 1，如下是等同的：  

```css
.item {flex: 11 32px;}
.item {
    flex-grow: 11;
    flex-shrink: 1;
    flex-basis: 32px;
}
```

建议优先使用这个属性，而不是单独写三个分离的属性。  

grow 和 shrink 是一对双胞胎，grow 表示伸张因子，shrink 表示是收缩因子。  

grow 在 flex 容器下的子元素的宽度和比容器和小的时候起作用。 grow 定义了子元素的尺寸增长因子，容器中除去子元素之和剩下的尺寸会按照各个子元素的 grow 值进行平分加大各个子元素上。





### Flex父元素的属性

display:flex  flex布局方式

flex-direction  主轴方向

flex-wrap 元素是否可换行

justify-content 主轴元素排列方式

align-items 交叉轴元素排列方式





## Flex子元素的属性

flex-grow 定义元素的放大比例，0为不会放大

flex-shrink 定义元素的缩小比例，0为不会缩小

flex-basis  定义了在分配多余空间之前，项目占据的主轴空间，浏览器根据这个属性，计算主轴是否有多余空间

flex   上三个属性值的组合。假设以上三个属性同样取默认值，则 flex 的默认值是 0 1 auto。

        有关快捷值：auto (1 1 auto) 和 none (0 0 auto)

align-self  改变子元素的交叉轴排列方式（覆盖父元素设置的）

order   改变元素的排列顺序（不用改变html排列）
