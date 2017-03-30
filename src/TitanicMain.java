import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class do the predict about the Titanic Kaggle problem.
 */

/**
 * @author Paul.Yang
 * @Email  shohokuooo@gmail.com
 * @Github https://github.com/PaulYangSz/MyJavaLogisticRegression
 *
 */
public class TitanicMain {

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        FileHelper readData = null;
        LinearMapFunction aLinearFunc = null;
        NonlinearMapFunction aNonlinearFunc = null;
        LogiRegModel   commGdModel = null;

        
        /** 
         * Fist: save the data read from a file.
         */
        if(DebugConfig.RUN_LINEAR_CASE) {
            readData = new FileHelper("./data/kaggle/gen_train_data.csv");
        }
        else {
            readData = new FileHelper("./data/kaggle/gen_train_data.csv");
        }
        
        /**
         * Second: put the data into a Map-Function
         */
        if(DebugConfig.RUN_LINEAR_CASE) {
            aLinearFunc = new LinearMapFunction(readData, "");
        }
        else {
            aNonlinearFunc = new NonlinearMapFunction(readData, "", 3);
        }
        
        /**
         * Third: Use a optimization method to train this Map-Function
         *        and get the Theta of Map-Function
         */
        GradientDecent useGdOpti = new GradientDecent();
        if(DebugConfig.RUN_LINEAR_CASE) {
            commGdModel = new LogiRegModel(aLinearFunc, useGdOpti);
        }
        else{
            commGdModel = new LogiRegModel(aNonlinearFunc, useGdOpti);
        }
        int opriIterTimes = 2000;
        double alpha = 0.01;
        System.out.printf("Begin optimizing with alpha=%f, iter_times=%d\n", alpha, opriIterTimes);
        commGdModel.startOpti(alpha, opriIterTimes);
        System.out.printf("After optimization, Theta[%d]:\n", commGdModel.mapF.theta.length);
        for(int j = 0; j < commGdModel.mapF.theta.length; j++) {
            System.out.println(commGdModel.mapF.theta[j]);
        }
        System.out.println("============");
        
        /**
         * Fourth: Use this trained model to predict original training data set.
         */
        int sumPrediCorr = 0;
        for(int i = 0; i < commGdModel.mapF.xData.length; i++) {
            if(commGdModel.predictClassify(commGdModel.mapF.xData[i]) == commGdModel.mapF.yData[i]) {
                sumPrediCorr++;
            }
            else
            {
                System.out.println("i= "+ i +",Predi = "+commGdModel.predictClassify(commGdModel.mapF.xData[i])+ ", Y= "+commGdModel.mapF.yData[i]);
            }
        }
        double prediRate = (double)sumPrediCorr / commGdModel.mapF.xData.length;
        System.out.println("Our model predict original training data correct rate is " + prediRate + ",num = " + sumPrediCorr);
        
        /**
         * Fifth: Predict the test data and submit the result to Kaggle.
         */
        FileHelper readTestData = null;
        readTestData = new FileHelper("./data/kaggle/gen_test_data.csv");
        int[] yResult = new int[readTestData.data.size()];
        int[] passId = new int[readTestData.data.size()];
        for(int i = 0; i < readTestData.data.size(); i++) {
            passId[i] = 892 + i;
            yResult[i] = commGdModel.predictClassify(readTestData.data.get(i));
        }

        Date now = new Date();
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy_MM_dd-HH_mm");
        FileHelper.writeToFile("PassengerId,Survived", passId, yResult, "./data/kaggle/Yang_submit-" + dateformat.format(now) + ".csv");
        
    }


}
