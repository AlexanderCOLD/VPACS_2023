package protection.model.logicalnodes.measurements;

import protection.model.dataobjects.measurements.MV;
import protection.model.dataobjects.measurements.WYE;
import protection.model.logicalnodes.common.LN;
import utils.filters.Filter;
import utils.filters.MeanFilter;

/**
 * @author Александр Холодов
 * @created 03/2023
 * @description
 */
public class MMXU extends LN {

    /*
     * Входы
     */
    public MV phsAInst = new MV();
    public MV phsBInst = new MV();
    public MV phsCInst = new MV();

    /*
     * Выходы
     */
    public final WYE A = new WYE();


    /*
     * Переменные
     */

    private final Filter phsACurrent = new MeanFilter();
    private final Filter phsBCurrent = new MeanFilter();
    private final Filter phsCCurrent = new MeanFilter();


    @Override
    public void process() {
        phsACurrent.process(phsAInst, A.getPhsA());
        phsBCurrent.process(phsBInst, A.getPhsB());
        phsCCurrent.process(phsCInst, A.getPhsC());
    }
}
