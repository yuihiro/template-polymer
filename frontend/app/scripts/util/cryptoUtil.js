'use strict';
class CryptoUtil {
    constructor() {}
    static getOptions() {
        var iv = CryptoJS.enc.Hex.parse('0000000000000000');
        var options = {
            mode: CryptoJS.mode.CBC,
            keySize: 256 / 32,
            padding: CryptoJS.pad.Pkcs7,
            iv: iv
        };
        return options;
    }
    static encrypt(text, key) {
        key = app.data.crypto_key;
        var hash_key = CryptoJS.SHA256(key);
        var encrypted = CryptoJS.AES.encrypt(text, hash_key, this.getOptions());
        return encrypted.toString();
    }
    static decrypt(text, key) {
        if(key != undefined) {
            this.key = key;
        }
        var hash_key = CryptoJS.SHA256(key);
        var decrypted = CryptoJS.AES.decrypt(text, hash_key, this.getOptions());
        return decrypted.toString(CryptoJS.enc.Utf8);
    }
}