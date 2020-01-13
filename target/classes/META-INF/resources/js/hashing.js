function hashsha256() {
    try {


        var formID = 'login-form';
        var usernameID = formID + ':username';
        var passwordID = formID + ':password';
        var hashsumID = formID + ':hashsum';
        var keyID = 'javax.faces.ViewState';

        hash1 = Sha256.hash(document.getElementById(passwordID).value);
        key=$("form[name='" + formID + "'] input[name='" + keyID + "']").attr('value');
//        key=document.formName.elements["'" + keyID + "'"].value
        hash2 = Sha256.hash(hash1 + key);
        document.getElementById(passwordID).value = hash2;
        hashsum = Sha256.hash(hash2 + document.getElementById(usernameID).value);
        document.getElementById(hashsumID).value = hashsum;
    } catch (Exception) {
    	console.error("Exception thrown", Exception.stack);

    }
    return true;
}