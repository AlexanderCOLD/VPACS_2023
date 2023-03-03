package protection.model.logicalnodes.protections;

import protection.model.dataobjects.measurements.WYE;
import protection.model.dataobjects.protection.ACD;
import protection.model.dataobjects.protection.ACT;
import protection.model.dataobjects.settings.ASG;
import protection.model.logicalnodes.common.LN;

/**
 * @author Александр Холодов
 * @created 03/2023
 * @description
 */
public class PTOC extends LN {

    /*
     * Входы
     */
    public WYE A = new WYE();

    public ASG StrVal = new ASG();
    /*public ING OpDlTmms = new ING(); TODO: Выдержка */

    /*
     * Выходы
     */
    public final ACD Str = new ACD();
    public final ACT Op = new ACT();

    /*
     * Переменные
     */

    @Override
    public void process() {

        Str.getPhsA().setValue(A.getPhsA().getCVal().getMag().getF().getValue() >
                StrVal.getSetMag().getF().getValue());
        Str.getPhsB().setValue(A.getPhsB().getCVal().getMag().getF().getValue() >
                StrVal.getSetMag().getF().getValue());
        Str.getPhsC().setValue(A.getPhsC().getCVal().getMag().getF().getValue() >
                StrVal.getSetMag().getF().getValue());

        Str.getGeneral().setValue(
                Str.getPhsA().getValue() || Str.getPhsB().getValue() || Str.getPhsC().getValue()
        );


        Op.getGeneral().setValue(Str.getGeneral().getValue());
        Op.getPhsA().setValue(Str.getPhsA().getValue());
        Op.getPhsB().setValue(Str.getPhsB().getValue());
        Op.getPhsC().setValue(Str.getPhsC().getValue());
    }
}
