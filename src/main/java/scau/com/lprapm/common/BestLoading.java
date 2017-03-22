package scau.com.lprapm.common;

/**
 * Created by 钟锐锋 on 2017/3/22.
 */

/**
 * 最优装载问题（贪心算法）
 *
 * @author Lican
 */
public class BestLoading {
    public float loading(float c, float[] w, int[] x) {
        int n = w.length;
        Element[] d = new Element[n];
        for (int i = 0; i < n; i++) {
            d[i] = new Element(w[i], i);
        }
        java.util.Arrays.sort(d);
        for (int i = 0; i < n; i++)
            x[i] = 0;
        float op = 0;
        for (int i = 0; i < n && d[i].w <= c; i++) {
            op += d[i].w;
            c -= d[i].w;
            x[d[i].i] = 1;
        }
        return op;
    }

    static class Element implements Comparable {
        float w;
        int i;

        public Element(float ww, int ii) {
            w = ww;
            i = ii;
        }

        @Override
        public int compareTo(Object x) {//按每个重量从小到大排列
            float xx = ((Element) x).w;
            if (this.w < xx) return -1;
            if (this.w == xx) return 0;
            return 1;
        }

    }

    public static int[] getIndex(float w[], float c) {
        int[] x = new int[w.length];
        int[] y = new int[w.length];
        int j = 0;
        BestLoading be = new BestLoading();
        System.out.println("最优得到装载重量为：" + be.loading(c, w, x));
        System.out.println("被装载的集装箱序号为(下标从0开始)：");
        for (int i = 0; i < w.length; i++) {
            if (x[i] == 1) {
                y[j++] = i;
                System.out.print(i + " ");
            }
        }
        return y;
    }
//    public static void main(String[] args) {
//        float w[] = {20,10,26,15};//下标从0开始
//        float c=70;
//        int[] x=new int[w.length];
//        BestLoading be= new BestLoading();
//        System.out.println("最优得到装载重量为："+be.loading(c, w, x));
//        System.out.println("被装载的集装箱序号为(下标从0开始)：");
//        for(int i=0;i<w.length;i++){
//            if(x[i]==1)
//                System.out.print(i+" ");
//        }
//    }
}
