package org.example;

import protection.model.common.DataAttribute;
import protection.model.logicalnodes.gui.NHMI;
import protection.model.logicalnodes.gui.other.NHMISignal;
import protection.model.logicalnodes.input.LCOM;
import protection.model.logicalnodes.common.LN;
import protection.model.logicalnodes.measurements.MMXU;
import protection.model.logicalnodes.protections.PTOC;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Александр Холодов
 * @created ${MONTH}/${YEAR}
 * @description
 */
public class Main {

    private final static List<LN> lnList = new ArrayList<>();

    public static void main(String[] args) {

        LCOM lcom = new LCOM();
        lnList.add(lcom);
        lcom.setFilePath(
                "C:\\Users\\Hols-\\YandexDisk\\Московский Энергетический Институт материалы\\Алгоритмы РЗА и их программная реализация\\Лабораторная работа №1\\Опыты\\Начало линии\\",
                "PhA80"
        );

        MMXU mmxu = new MMXU();
        lnList.add(mmxu);
        mmxu.phsAInst = lcom.OUT.get(0);
        mmxu.phsBInst = lcom.OUT.get(1);
        mmxu.phsCInst = lcom.OUT.get(2);

        PTOC ptoc1 = new PTOC();
        lnList.add(ptoc1);
        ptoc1.StrVal.getSetMag().getF().setValue(500d);
        ptoc1.A = mmxu.A;

        NHMI nhmi = new NHMI();
        lnList.add(nhmi);
        nhmi.addSignals(
                new NHMISignal("InstValuePhsA", lcom.OUT.get(0).getInstMag().getF()),
                new NHMISignal("RmsValuePhsA", mmxu.A.getPhsA().getCVal().getMag().getF()));
        nhmi.addSignals(
                "График",
                new NHMISignal("Trip", ptoc1.Op.getGeneral()));

        while (lcom.hasNextData()) {
            lnList.forEach(LN::process);
        }
    }
}