public class Perm {

	private static int count = 0;
	
	public static void main (String [] args)  {
		
		String[] letters = {"L","R"};
		String[] lists = permList(letters, 3);
//		
//		for (int j=0; j<lists.length;j++) {
//			System.out.println(lists[j]);
//		}
//		
//		String[] letters1 = {"1","3","5","7","9"};
//		String[] lists1 = permList(letters1, 3);
//		
//		for (int j=0; j<lists1.length;j++) {
//			System.out.println(lists1[j]);
//		}
//		
		String[] letters2 = {"1","3","5","7","9"};
		String[] lists2 = permList(letters2, 12);
		
		long timer = System.currentTimeMillis();
		for (int j=0; j<lists2.length;j++) {
			//starts with 1, replace with 2
			if(lists2[j].indexOf("1")==0){
				lists2[j] = "2"+lists2[j].substring(1);
			}
			boolean p = true;
			for (int i=1; i<=lists2[j].length();i++) {
				if (!isPrime(Long.parseLong(lists2[j].substring(0,i)))) {
					p = false;
					break;
				}
			}
			if (p) {
				//System.out.println(lists2[j]);
				count ++;
			}
		}
		timer = System.currentTimeMillis() - timer;
		System.out.println(""+timer);
	}
	
	public static String[] permList(String[] letters, int len) {
		String[] lists=new String[(int)Math.pow(letters.length, len)];
		
		if (len==1)
			return letters;
		else {
			String[] subLists = permList(letters, len-1);
			
			int idx = 0;
			for (int i=0; i<letters.length;i++) {
				for (int j=0; j<subLists.length;j++) {
					lists[idx] = letters[i]+subLists[j];
					idx++;
				}
			}
		}
		return lists;
	}
	
	public static boolean isPrime(long n) {
		if (n<2) 
			return false;
		if (n==2 || n==3)
			return true;
		if (n%2==0 || n%3==0)
			return false;
		long sq = (long)Math.sqrt(n)+1;
		for (long i=6L; i<=sq;i+=6) {
			if (n%(i-1)==0 || n%(i+1)==0)
				return false;
		}
		return true;
	}
	
	public static boolean isPrime1(long n) {
		int numfactors = 0;
		
		long divisor = 2;
		while (divisor < n) {
			if (n % divisor ==0)
				numfactors ++;
			divisor++;
			if (numfactors>=1)
				return false;
		}
		
		return true;
	}
}
