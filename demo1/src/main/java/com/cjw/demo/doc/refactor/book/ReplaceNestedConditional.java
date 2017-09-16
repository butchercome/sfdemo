package com.cjw.demo.doc.refactor.book;

/**
 * Created by Javen on 2017/6/8.
 */
public class ReplaceNestedConditional {
//    double GetPayAmount() {
//        double result;
//        if (_isDead) result = deadAmount();
//        else {
//            if (_isSeparated) result = separatedAmount();
//            else {
//                if (_isRetired) result = retiredAmount();
//                else result = normalPayAmount();
//            }
//        }
//        return result;
//    }
//
//    public double getAgjustedCaption() {
//        double result = 0.0;
//        if (_capital > 0.0) {
//            if (_iniRate > 0.0 && _duration > 0.0) {
//                result = (_income / _duration) * ADJ_Factor;
//            }
//        }
//        return result;
//    }
//
//    public String getTelphoneNumber() {
//        return ("{" + _officeAreaCode + ")" + _officeNumber);
//    }
//
//    class ReplaceNestedConditionalRefactor {
//        double GetPayAmount() {
//            if (_isDead) return deadAmount();
//            if (_isSeparated) return separatedAmount();
//            if (_isRetired) return retiredAmount();
//            return normalPayAmount();
//        }
//
//        public double getAgjustedCaption() {
//            double result = 0.0;
//            if (_capital <= 0.0) return 0.0;
//            if (_iniRate <= 0.0 || _duration <= 0.0) return 0.0;
//            return (_income / _duration) * ADJ_Factor;
//        }
//
//        public String getTelphoneNumber() {
//            return getOfficeTelNumber();
//        }
//
//        public String getOfficeTelNumber() {
//            return ("{" + _officeAreaCode + ")" + _officeNumber);
//        }
//    }
}
