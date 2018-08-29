package com.yunlongstudio.algorithm;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.Stack;


public class ProjectEuler {

  public static void main(String[] args) {
    /*
    System.out.println("Problem #1 = " + Problem1());
    System.out.println("Problem #2 = " + Problem2());
    System.out.println("Problem #3 = " + Problem3());
    System.out.println("Problem #4 = " + Problem4());
    System.out.println("Problem #5 = " + Problem5());
    System.out.println("Problem #6 = " + Problem6());
    System.out.println("Problem #7 = " + Problem7());
    System.out.println("Problem #8 = " + Problem8());
    System.out.println("Problem #9 = " + Problem9());
    System.out.println("Problem #10 = " + Problem10());
    System.out.println("Problem #11 = " + Problem11());
    System.out.println("Problem #12 = " + Problem12());
    System.out.println("Problem #13 = " + Problem13());
    System.out.println("Problem #14 = " + Problem14());
    System.out.println("Problem #15 = " + Problem15());
    System.out.println("Problem #16 = " + Problem16());
    System.out.println("Problem #17 = " + Problem17());
    System.out.println("Problem #18 = " + Problem18());
    System.out.println("Problem #19 = " + Problem19());
    System.out.println("Problem #20 = " + Problem20());
    */

    System.out.println("Problem #21 = " + Problem21());    
  }

  public static long Problem1() {
    long ret = 0;
    for (int i = 1; i < 1000; i++) {
    	if (i % 3 == 0) {
    		ret += i;
    	} else if (i % 5 == 0) {
    		ret += i;
    	}
    }
    
    return ret;
  }

  public static long Problem2() {
    long ret = 0;

    int pre_fibonacci = 1;
    int fibonacci = 2;
    while (fibonacci < 4000000) {
    
      if (fibonacci % 2 == 0) {
        ret += fibonacci;
      }
      
      int temp = fibonacci;
      fibonacci += pre_fibonacci;
      pre_fibonacci = temp;
    }

    return ret;
  }

  private static boolean isPrime(long d) {
    for(int j = 2; j <= Math.sqrt(d); j++) {
      if (d%j == 0) {   
        return false;
      }
    }
    
    return true;
  }

  public static long Problem3() {
    long ret = 0;

    long i = 1;
    while (i < Math.sqrt(600851475143L)) {
      if (600851475143L % i == 0 && isPrime(i)) {
        ret = i;
        System.out.println("i = " + i);
      }
      i++;
    }

    return ret;
  }

  public static int Problem4() {
    int ret = 0;
    for (int i = 999; i > 0; i--) {
      for (int j = 999;  j > 0; j--) {
        int value = i * j;
        char[] chars = Integer.toString(value).toCharArray();
        boolean isPalindrome = true;
        for (int k = 0; k < chars.length / 2; k++) {
          if (chars[k] != chars[chars.length - 1 - k]) {
            isPalindrome = false;
            break;
          }
        }
        
        if (isPalindrome) {
          System.out.println("i = " + i);
          System.out.println("j = " + j);
          if (value > ret) {
            ret = value;
          }
        }
      }
    }
    
    return ret;
  }
  
  public static int Problem5() {
    int i;

    for (i = 1; ;i++) {
      boolean ok = true;
      for (int j = 1; j < 20; j++) {
        if (i%j != 0) {
          ok = false;
          break;
        }
      }
      
      if (ok) {
        break;
      }
    }
    
    return i;
  }

  public static int Problem6() {
    int a = 0;
    int b = 0;

    for (int i = 1; i <= 100; i++) {
      a += i;
    }
    a = a * a;
    
    for (int i = 1; i <= 100; i++) {
      b += i * i;
    }
    
    return a - b;
  }

  public static int Problem7() {
    int count = 0;
    int i;
    for (i = 2; ;i++) {
      if (isPrime(i)) {
        count++;
      }
      
      if (count == 10001) {
        break;
      }
    }
    
    return i;
  }

  public static int Problem8() {
    String numbers = "73167176531330624919225119674426574742355349194934" + 
        "96983520312774506326239578318016984801869478851843" + 
        "85861560789112949495459501737958331952853208805511" +
        "12540698747158523863050715693290963295227443043557" +
        "66896648950445244523161731856403098711121722383113" +
        "62229893423380308135336276614282806444486645238749" +
        "30358907296290491560440772390713810515859307960866" +
        "70172427121883998797908792274921901699720888093776" +
        "65727333001053367881220235421809751254540594752243" +
        "52584907711670556013604839586446706324415722155397" +
        "53697817977846174064955149290862569321978468622482" +
        "83972241375657056057490261407972968652414535100474" +
        "82166370484403199890008895243450658541227588666881" +
        "16427171479924442928230863465674813919123162824586" +
        "17866458359124566529476545682848912883142607690042" +
        "24219022671055626321111109370544217506941658960408" +
        "07198403850962455444362981230987879927244284909188" +
        "84580156166097919133875499200524063689912560717606" +
        "05886116467109405077541002256983155200055935729725" +
        "71636269561882670428252483600823257530420752963450";
    
    char[] chars = numbers.toCharArray();
    int ret = 0;
    for (int i = 0; i < chars.length - 6; i++) {
      int temp = (chars[i] - '0') *
                 (chars[i+1] - '0') *
                 (chars[i+2] - '0') *
                 (chars[i+3] - '0') *
                 (chars[i+4] - '0');
      if (temp > ret) {
        ret = temp;
      }
    }

    return ret;
  }

  public static int Problem9() {
    int aa, bb, cc;
    for (int a = 1; a < 1000 / 3; a++) {
      for (int b = a; b < 1000 - a; b++) {
        if (a * a + b * b == (1000 - a - b) * (1000 - a - b)) {
          aa = a;
          bb = b;
          cc = 1000 - a - b;
          return aa * bb * cc;
        }
      }
      
    }
    
    return 0;
  }

  public static double Problem10() {
    double ret = 0;
    for (int i = 2; i < 200 * 10000; i++) {
      if (isPrime(i)) {
        ret += i;
      }
    }
    
    return ret;
  }
  
  public static double Problem11() {
    double ret = 0;
    
    int[][] num = {
        {8,02,22,97,38,15,00,40,00,75,04,05,07,78,52,12,50,77,91,8},
        {49, 49, 99, 40, 17, 81, 18, 57 ,60, 87 ,17, 40 ,98 ,43, 69 ,48, 04 ,56, 62 ,00},
        {81, 49, 31, 73, 55, 79, 14, 29 ,93, 71 ,40 ,67 ,53 ,88, 30 ,03 ,49 ,13, 36 ,65},
        {52, 70, 95, 23, 04, 60, 11, 42 ,69 ,24 ,68 ,56 ,01 ,32, 56 ,71, 37 ,02, 36 ,91},
        {22, 31, 16, 71, 51, 67, 63, 89 ,41 ,92 ,36 ,54 ,22 ,40, 40 ,28 ,66 ,33, 13 ,80},
        {24, 47, 32, 60, 99, 03, 45, 02 ,44, 75 ,33 ,53 ,78 ,36, 84 ,20 ,35 ,17, 12 ,50},
        {32, 98, 81, 28, 64, 23, 67, 10 ,26, 38 ,40 ,67 ,59 ,54, 70 ,66 ,18 ,38, 64 ,70},
        {67, 26, 20, 68, 02, 62, 12, 20 ,95, 63 ,94 ,39 ,63 ,8 ,40 ,91 ,66 ,49, 94 ,21},
        {24, 55, 58, 5, 66, 73, 99, 26 ,97, 17 ,78 ,78 ,96 ,83 ,14 ,88 ,34 ,89, 63 ,72},
        {21, 36, 23, 9, 75, 00, 76, 44, 20, 45, 35 ,14 ,00 ,61 ,33 ,97 ,34 ,31, 33 ,95},
        {78, 17, 53, 28, 22, 75, 31, 67, 15, 94, 03 ,80 ,04 ,62 ,16 ,14 ,9 ,53, 56 ,92},
        {16, 39, 5, 42, 96, 35, 31, 47, 55, 58, 88 ,24 ,00 ,17 ,54 ,24 ,36 ,29, 85 ,57},
        {86, 56, 00, 48, 35, 71, 89, 07, 05, 44, 44 ,37, 44 ,60 ,21 ,58 ,51 ,54, 17 ,58},
        {19, 80, 81, 68, 05, 94, 47, 69, 28, 73, 92 ,13, 86 ,52 ,17 ,77 ,04 ,89, 55 ,40},
        {04, 52, 8, 83, 97, 35, 99, 16, 07, 97, 57, 32, 16, 26, 26, 79, 33, 27, 98, 66},
        {88, 36, 68, 87, 57, 62, 20, 72, 03, 46, 33 ,67 ,46 ,55 ,12 ,32 ,63 ,93, 53 ,69},
        {04, 42, 16, 73, 38, 25, 39, 11, 24, 94, 72, 18 ,8 ,46 ,29 ,32 ,40 ,62 ,76 ,36},
        {20, 69, 36, 41, 72, 30, 23, 88, 34, 62, 99, 69 ,82 ,67 ,59 ,85 ,74, 04 ,36 ,16},
        {20, 73, 35, 29, 78, 31, 90, 01, 74, 31, 49, 71 ,48, 86, 81 ,16 ,23, 57, 05, 54},
        {01, 70, 54, 71, 83, 51, 54, 69, 16, 92, 33, 48 ,61, 43 ,52 ,01 ,89, 19, 67, 48}
    };
    
    for (int i = 0; i < 20; i++) {
      for (int j = 0; j < 20 - 3; j++) {
        int temp = num[i][j] * num[i][j + 1] * num[i][j + 2] * num[i][j + 3];
        if (temp > ret) {
          ret = temp;
        }
      }
    }
    
    for (int i = 0; i < 20 - 3; i++) {
      for (int j = 0; j < 20; j++) {
        int temp = num[i][j] * num[i + 1][j] * num[i + 2][j] * num[i + 3][j];
        if (temp > ret) {
          ret = temp;
        }
      }
    }

    for (int i = 0; i < 20 - 3; i++) {
      for (int j = 0; j < 20 - 3; j++) {
        int temp = num[i][j] * num[i + 1][j + 1] * num[i + 2][j + 2] * num[i + 3][j + 3];
        if (temp > ret) {
          ret = temp;
        }
      }
    }
    
    for (int i = 0; i < 20 - 3; i++) {
      for (int j = 3; j < 20; j++) {
        int temp = num[i][j] * num[i + 1][j - 1] * num[i + 2][j - 2] * num[i + 3][j - 3];
        if (temp > ret) {
          ret = temp;
        }
      }
    }

    return ret;
  }

  public static int Problem12() {
    int triangle_number = 0;
    for (int i = 1; ;i++) {
      triangle_number += i;
      int count_divisors = 0;
      for (int j = 1; j <= triangle_number; j++) {
        if (triangle_number % j == 0) {
          count_divisors++;
        }
      }
      System.out.println("i = " + i);
      System.out.println("triangle number = " + triangle_number + ", divisors = " + count_divisors);
      if (count_divisors > 500) {
        break;
      }
    }
    
    return triangle_number;
  }

  public static int Problem13() {
    String[] strings = {
    "37107287533902102798797998220837590246510135740250",
    "46376937677490009712648124896970078050417018260538",
    "74324986199524741059474233309513058123726617309629",
    "91942213363574161572522430563301811072406154908250",
    "23067588207539346171171980310421047513778063246676",
    "89261670696623633820136378418383684178734361726757",
    "28112879812849979408065481931592621691275889832738",
    "44274228917432520321923589422876796487670272189318",
    "47451445736001306439091167216856844588711603153276",
    "70386486105843025439939619828917593665686757934951",
    "62176457141856560629502157223196586755079324193331",
    "64906352462741904929101432445813822663347944758178",
    "92575867718337217661963751590579239728245598838407",
    "58203565325359399008402633568948830189458628227828",
    "80181199384826282014278194139940567587151170094390",
    "35398664372827112653829987240784473053190104293586",
    "86515506006295864861532075273371959191420517255829",
    "71693888707715466499115593487603532921714970056938",
    "54370070576826684624621495650076471787294438377604",
    "53282654108756828443191190634694037855217779295145",
    "36123272525000296071075082563815656710885258350721",
    "45876576172410976447339110607218265236877223636045",
    "17423706905851860660448207621209813287860733969412",
    "81142660418086830619328460811191061556940512689692",
    "51934325451728388641918047049293215058642563049483",
    "62467221648435076201727918039944693004732956340691",
    "15732444386908125794514089057706229429197107928209",
    "55037687525678773091862540744969844508330393682126",
    "18336384825330154686196124348767681297534375946515",
    "80386287592878490201521685554828717201219257766954",
    "78182833757993103614740356856449095527097864797581",
    "16726320100436897842553539920931837441497806860984",
    "48403098129077791799088218795327364475675590848030",
    "87086987551392711854517078544161852424320693150332",
    "59959406895756536782107074926966537676326235447210",
    "69793950679652694742597709739166693763042633987085",
    "41052684708299085211399427365734116182760315001271",
    "65378607361501080857009149939512557028198746004375",
    "35829035317434717326932123578154982629742552737307",
    "94953759765105305946966067683156574377167401875275",
    "88902802571733229619176668713819931811048770190271",
    "25267680276078003013678680992525463401061632866526",
    "36270218540497705585629946580636237993140746255962",
    "24074486908231174977792365466257246923322810917141",
    "91430288197103288597806669760892938638285025333403",
    "34413065578016127815921815005561868836468420090470",
    "23053081172816430487623791969842487255036638784583",
    "11487696932154902810424020138335124462181441773470",
    "63783299490636259666498587618221225225512486764533",
    "67720186971698544312419572409913959008952310058822",
    "95548255300263520781532296796249481641953868218774",
    "76085327132285723110424803456124867697064507995236",
    "37774242535411291684276865538926205024910326572967",
    "23701913275725675285653248258265463092207058596522",
    "29798860272258331913126375147341994889534765745501",
    "18495701454879288984856827726077713721403798879715",
    "38298203783031473527721580348144513491373226651381",
    "34829543829199918180278916522431027392251122869539",
    "40957953066405232632538044100059654939159879593635",
    "29746152185502371307642255121183693803580388584903",
    "41698116222072977186158236678424689157993532961922",
    "62467957194401269043877107275048102390895523597457",
    "23189706772547915061505504953922979530901129967519",
    "86188088225875314529584099251203829009407770775672",
    "11306739708304724483816533873502340845647058077308",
    "82959174767140363198008187129011875491310547126581",
    "97623331044818386269515456334926366572897563400500",
    "42846280183517070527831839425882145521227251250327",
    "55121603546981200581762165212827652751691296897789",
    "32238195734329339946437501907836945765883352399886",
    "75506164965184775180738168837861091527357929701337",
    "62177842752192623401942399639168044983993173312731",
    "32924185707147349566916674687634660915035914677504",
    "99518671430235219628894890102423325116913619626622",
    "73267460800591547471830798392868535206946944540724",
    "76841822524674417161514036427982273348055556214818",
    "97142617910342598647204516893989422179826088076852",
    "87783646182799346313767754307809363333018982642090",
    "10848802521674670883215120185883543223812876952786",
    "71329612474782464538636993009049310363619763878039",
    "62184073572399794223406235393808339651327408011116",
    "66627891981488087797941876876144230030984490851411",
    "60661826293682836764744779239180335110989069790714",
    "85786944089552990653640447425576083659976645795096",
    "66024396409905389607120198219976047599490197230297",
    "64913982680032973156037120041377903785566085089252",
    "16730939319872750275468906903707539413042652315011",
    "94809377245048795150954100921645863754710598436791",
    "78639167021187492431995700641917969777599028300699",
    "15368713711936614952811305876380278410754449733078",
    "40789923115535562561142322423255033685442488917353",
    "44889911501440648020369068063960672322193204149535",
    "41503128880339536053299340368006977710650566631954",
    "81234880673210146739058568557934581403627822703280",
    "82616570773948327592232845941706525094512325230608",
    "22918802058777319719839450180888072429661980811197",
    "77158542502016545090413245809786882778948721859617",
    "72107838435069186155435662884062257473692284509516",
    "20849603980134001723930671666823555245252804609722",
    "53503534226472524250874054075591789781264330331690"
    };
    
    int[][] numbers = new int[strings.length][strings[0].length()];
    for (int i = 0; i < strings.length; i++) {
      char[] chars = strings[i].toCharArray();
      for (int j = 0; j < chars.length; j++) {
        numbers[i][j] = chars[j] - '0';
      }
    }
    
    Stack<Integer> result = new Stack<Integer>();
    
    int r = sum(result, numbers, numbers[0].length - 1, 0);
    
    System.out.print(r);
    for (int i = 0; i < 10; i++) {
      System.out.print(result.pop());
    }
    return 0;
  }

  private static int sum(Stack<Integer> result, int[][] numbers, int pos, int increase) {
    if (numbers == null || numbers.length < 1 || numbers[0].length < pos) {
      return 0;
    }
    if (pos < 0) {
      return increase;
    }

    int temp = 0;
    for (int i = 0; i < numbers.length; i++) {
      temp += numbers[i][pos];
    }
    temp += increase;
    
    result.push(temp % 10);
    return sum(result, numbers, pos - 1, temp / 10);
  }

  public static long Problem14() {
    long sequenceLength = 0;
    long startingNumber = 0;
    long sequence;

    for (long i = 2; i <= 1000000; i++) {
      int length = 1;
      sequence = i;
      while (sequence != 1) {
        if ((sequence % 2) == 0) {
          sequence = sequence / 2;
        } else {
          sequence = sequence * 3 + 1;
        }
        length++;
      }
     
      //Check if sequence is the best solution
      if (length > sequenceLength) {
        sequenceLength = length;
        startingNumber = i;
      }
    }
    
    return startingNumber;
  }

  public static long Problem15() {
    int num = 20;
    long[][] grids = new long[num + 1] [num + 1];
    for (int i = 0; i < num + 1; i++) {
      grids[i][0] = 1;
      grids[0][i] = 1;
    }
    
    for (int i = 1; i < num + 1; i++) {
      for (int j = 1; j < num + 1; j++) {
        grids[i][j] = grids[i-1][j] + grids[i][j-1];
      }
    }

    return grids[num][num];
  }
  
  public static int Problem16() {
    byte[] num = {1};
    BigInteger ret = new BigInteger(num);
    for (int i = 0; i < 1000; i++) {
      ret = ret.add(ret);
    }

    char[] digit = ret.toString().toCharArray();
    int sum = 0;
    for (int i = 0; i < digit.length; i++) {
      sum += digit[i] - '0';
    }

    return sum;
  }

  public static long Problem17() {
    return 0;
  }

  private static String translate(int num) {
    if (num < 1) {
      throw new IllegalArgumentException("unsupported parameter");
    }

    final String[] ONETONINE = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    final String[] ELEVENTONINETEEN = {"eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    final String[] TENTONINTY = {"ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
    final String HUNDRED = "hundred";
    final String THOUSAND = "thousand";
    final String MILLION = "million";
    final String BILLION = "billion";
    final String AND = "and";
    final String HYPHEN = "-";

    int i = num / 10;
    int j = num % 10;
    if (i == 0) {
      return ONETONINE[j - 1];
    } else if (i == 1) {
      return ELEVENTONINETEEN[j - 1];
    }
    
    return "";
  }

  public static int Problem18() {
    int[][] numbers = { 
        {75},
        {95, 64},
        {17, 47, 82},
        {18, 35, 87, 10},
        {20, 04, 82, 47, 65},
        {19, 01, 23, 75, 03, 34},
        {88, 02, 77, 73, 07, 63, 67},
        {99, 65, 04, 28, 06, 16, 70, 92},
        {41, 41, 26, 56, 83, 40, 80, 70, 33},
        {41, 48, 72, 33, 47, 32, 37, 16, 94, 29},
        {53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14},
        {70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57},
        {91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48},
        {63, 66, 04, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31},
        {04, 62, 98, 27, 23, 9, 70, 98, 73, 93, 38, 53, 60, 4, 23}};

    return maxpath(numbers, 0, 0);
  }
  
  private static int maxpath(int[][] numbers, int i, int j) {
    if (i == numbers.length - 1) {
      if (j == numbers.length - 1) {
        return Math.max(numbers[i][j], 0);
      } else {
        return Math.max(numbers[i][j], numbers[i][j + 1]);
      }
    } else {
      return numbers[i][j] + Math.max(maxpath(numbers, i+1, j), maxpath(numbers, i+1, j+1));
    }
  }

  public static int Problem19() {
    int sundays = 0;
  
    for (int year = 1901; year <= 2000; year++) {
      for (int month = 1; month <= 12; month++) {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.YEAR, year);
        ca.set(Calendar.MONTH, month);
        ca.set(Calendar.DATE, 1);
        
        
          if (ca.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
              sundays++;
          }

      }
    }

    return sundays;
  }

  public static int Problem20() {
    byte[] num = {1};
    BigInteger ret = new BigInteger(num);
    
    for (int i = 1; i <= 100; i++) {
      ret = ret.multiply(new BigInteger("" + i));
    }

    char[] digit = ret.toString().toCharArray();
    int sum = 0;
    for (int i = 0; i < digit.length; i++) {
      sum += digit[i] - '0';
    }

    return sum;
  }

  public static int Problem21() {
    int sumAmicible = 0;
    int factorsi, factorsj;
     
    for (int i = 2; i <= 10000; i++) {
        factorsi = sumOfFactors(i);
        if (factorsi > i && factorsi <= 10000) {
            factorsj = sumOfFactors(factorsi);
            if (factorsj == i) {
                sumAmicible += i + factorsi;
            }
        }
    }
    
    return sumAmicible;
  }
  
  
  private static int sumOfFactors(int number) {
    int sqrtOfNumber = (int)Math.sqrt(number);
    int sum = 1;
 
    //If the number is a perfect square
    //Count the squareroot once in the sum of factors
    if (number == sqrtOfNumber * sqrtOfNumber) {
        sum += sqrtOfNumber;
        sqrtOfNumber--;
    }
 
    for (int i = 2; i <= sqrtOfNumber; i++) {
        if (number % i == 0) {
            sum = sum + i + (number / i);
        }
    }
 
    return sum;
}
}
