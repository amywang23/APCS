class floodfill{ 
  
    static int m = 3; 
    static int n = 3; 
      
    static void floodFill(int a[][], int x, int y, int prevC, int newC){ 
         
        if(x<0 || x>=m || y<0 || y>=n) 
            return; 
        if(a[x][y] != prevC) 
            return; 
      
        a[x][y] = newC; 
      
        floodFill(a, x+1, y, prevC, newC); 
        floodFill(a, x-1, y, prevC, newC); 
        floodFill(a, x, y+1, prevC, newC); 
        floodFill(a, x, y-1, prevC, newC); 
    } 
      
    static void Fill(int a[][], int x, int y, int color){ 
        int prevColor = a[x][y]; 
        floodFill(a, x, y, prevColor, color); 
    } 
      
    public static void main(String[] args){ 
        int a[][] = {{1, 1, 1}, 
                        {1, 1, 0}, 
                        {1, 0, 1}, 
                        }; 
        int x = 0, y = 0, color = 2; 
        Fill(a, x, y, color); 
      
        for(int i=0; i<m; i++){ 
            System.out.print("[ ");
            for(int j=0; j<n; j++) 
                System.out.print(a[i][j]+" "); 
            System.out.print("]");    
            System.out.println(); 
        } 
    } 
}