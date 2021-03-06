/**
 * This is a gradient-decent method to get the optimal Theta
 */

/**
 * @author Yang
 *
 */
public class GradientDecent extends OptiMethod{

    /**
     * No need to construct only provide the Gradient Decent method.
     */
    public GradientDecent() {
        // TODO Auto-generated constructor stub
    }

    @Override
    /**
     * Do gradient decent, stop condition is the iteration.
     */
    void doOpti(MapFunction mapF, double alpha, int iteration) {
        //Total calculate iteration times
        for(int iter = 0; iter < iteration; iter++) {
            
            if(DebugConfig.RECORD_OPTI_STEP) {
                if(iter % (iteration/10) == 0) {
                    System.out.printf("doing %dth optimizing...\n", iter);
                    /*
                    double cost = 0;
                    double hypothesis = 0;
                    for(int i = 0; i < mapF.yData.length; i++) {
                        hypothesis = MyMathApi.sigmoid(mapF.thetaTx(i));
                        if(mapF.yData[i] == 1) {
                            cost += Math.log(hypothesis);
                        }
                        else {
                            cost += Math.log(1 - hypothesis);
                        }
                    }
                    
                    System.out.println("cost = " + ( -(cost/mapF.yData.length) ) );
                    */
                }
            }
            //GD the theta_j
            for(int j = 0; j < mapF.thetaLen; j++) {
                double tmpSum = 0;
                for(int i = 0; i < mapF.yData.length; i++)
                {
                    double hypothesis = MyMathApi.sigmoid(mapF.thetaTx(i));
                    double error = hypothesis - mapF.yData[i];
                    double x_j = mapF.facList.get(j).calcXj(i);
                    tmpSum += error * x_j;
                }
                mapF.theta[j] -= alpha * tmpSum;
            }
            
            if(DebugConfig.RECORD_OPTI_STEP) {
                if(iter % (iteration/10) == 0) {
                    System.out.printf("done %dth optimizing\n", iter);
                    /*
                    double cost = 0;
                    double hypothesis = 0;
                    for(int i = 0; i < mapF.yData.length; i++) {
                        hypothesis = MyMathApi.sigmoid(mapF.thetaTx(i));
                        if(mapF.yData[i] == 1) {
                            cost += Math.log(hypothesis);
                        }
                        else {
                            cost += Math.log(1 - hypothesis);
                        }
                    }
                    
                    System.out.println("cost = " + ( -(cost/mapF.yData.length) ) );
                    */
                }
            }
        }
    }

    @Override
    /**
     * Do gradient decent, stop condition is the minus value of cost-function.
     */
    void doOpti(MapFunction mapF) {
        // TODO Auto-generated method stub
        return;
    }

    @Override
    void doOpti(MapFunction mapF, double alpha, int iteration, double[] w) {
        //Total calculate iteration times
        for(int iter = 0; iter < iteration; iter++) {

            if(DebugConfig.RECORD_OPTI_STEP) {
                if(iter % (iteration/10) == 0) {
                    System.out.printf("doing %dth optimizing...\n", iter);
                    /*
                    double cost = 0;
                    double hypothesis = 0;
                    for(int i = 0; i < mapF.yData.length; i++) {
                        hypothesis = MyMathApi.sigmoid(mapF.thetaTx(i));
                        if(mapF.yData[i] == 1) {
                            cost += Math.log(hypothesis);
                        }
                        else {
                            cost += Math.log(1 - hypothesis);
                        }
                    }

                    System.out.println("cost = " + ( -(cost/mapF.yData.length) ) );
                    */
                }
            }
            //GD the theta_j
            for(int j = 0; j < mapF.thetaLen; j++) {
                double tmpSum = 0;
                for(int i = 0; i < mapF.yData.length; i++)
                {
                    double hypothesis = MyMathApi.sigmoid(mapF.thetaTx(i));
                    double error = hypothesis - mapF.yData[i];
                    double x_j = mapF.facList.get(j).calcXj(i);
                    tmpSum += w[i] * error * x_j;
                }
                mapF.theta[j] -= alpha * tmpSum;
            }

            if(DebugConfig.RECORD_OPTI_STEP) {
                if(iter % (iteration/10) == 0) {
                    System.out.printf("done %dth optimizing\n", iter);
                    /*
                    double cost = 0;
                    double hypothesis = 0;
                    for(int i = 0; i < mapF.yData.length; i++) {
                        hypothesis = MyMathApi.sigmoid(mapF.thetaTx(i));
                        if(mapF.yData[i] == 1) {
                            cost += Math.log(hypothesis);
                        }
                        else {
                            cost += Math.log(1 - hypothesis);
                        }
                    }

                    System.out.println("cost = " + ( -(cost/mapF.yData.length) ) );
                    */
                }
            }
        }
    }

}
