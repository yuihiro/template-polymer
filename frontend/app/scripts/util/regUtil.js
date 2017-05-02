'use strict';
class RegUtil {
    static getEmailReg() {
        return "[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{1,3}$";
    }
    static getPassword() {
        return "(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$";
    }
    static getMacReg() {
        return "^((([0-9A-Fa-f]{2}:){5})|(([0-9A-Fa-f]{2}-){5}))[0-9A-Fa-f]{2}$";
    }
}