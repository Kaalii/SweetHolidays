function validation(f) {
  if (f.mdp.value == '' || f.mdp1.value == '') {
    alert('Tous les champs ne sont pas remplis');
    f.mdp.focus();
    return false;
    }
  else if (f.mdp.value != f.mdp1.value) {
    alert('Les deux mots de passes doivent être identiques!');
    f.mdp.focus();
    return false;
    }
  else if (f.mdp.value == f.mdp1.value) {
    return true;
    }
  else {
    f.mdp.focus();
    return false;
    }
 }