package D_DataProcess.AveragePrice.tool;

import D_DataProcess.AveragePrice.mapper.APMapper;
import D_DataProcess.AveragePrice.reducer.ApReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobStatus;
import org.apache.hadoop.util.Tool;

/**
 * @author Yixuan Huang
 * @date 2021/4/28 14:19
 */
public class APTool implements Tool {
    public int run(String[] args) throws Exception {

        //Create the job instance
        Job job = Job.getInstance();
        job.setJarByClass(APTool.class);

        //mapper
        TableMapReduceUtil.initTableMapperJob(
                "t_city_hotels_info",
                new Scan(),
                APMapper.class,
                ImmutableBytesWritable.class,
                DoubleWritable.class,
                job
        );

        //reducer
        TableMapReduceUtil.initTableReducerJob(
                "AveragePrice",
                ApReducer.class,
                job
        );

        //execute
        boolean status = job.waitForCompletion(true);
        return status? JobStatus.State.SUCCEEDED.getValue(): JobStatus.State.FAILED.getValue();
    }

    public void setConf(Configuration conf) {

    }

    public Configuration getConf() {
        return null;
    }
}
