package E_DataVisualization;

import E_DataVisualization.Build_WordCloud.CommentWordCloud;
import com.kennycason.kumo.WordCloud;


/**
 * @author Yixuan Huang
 * @date 2021/5/7 17:05
 */
public class Run {
    public static void main(String[] args) throws Exception {
        WordCloud wordCloud = new CommentWordCloud().get();
    }
}
