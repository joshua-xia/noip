//How to calculate the time of the programe?


    clock_t a = clock();
    init1();
    clock_t b = clock();
    printf("普通筛： %f\n",(double)(b-a)/CLOCKS_PER_SEC);
    a = clock();
    init2();
    b = clock();
    printf("欧拉筛： %f\n",(double)(b-a)/CLOCKS_PER_SEC);
   // CLOCKS_PER_SEC 是一秒钟的时钟数
