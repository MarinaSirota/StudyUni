
function validate_FIO( )
{
    let valid = true;
    let fioFormat=/^[а-яА-ЯёЁ]+\s+[а-яА-ЯёЁ]+\s+[а-яА-ЯёЁ]+$/;
    let checkFio=fioFormat.test(document.form_contact.name.value);

    if ( (document.form__test.name.value === "") ||
        (document.form__test.name.value.split(' ').length !== 3)
        || (checkFio===false ))
    {
        alert ( "Ошибка! Пожалуйста, введите данные в поле 'ФИО'." );
        document.form__test.name.value === "";
        document.form__test.name.focus();
        valid = false

    }


    return valid;
}

function validate_tests() {
    validate_FIO();
   let valid=true;
   let ar=new Array(2);
   ar[0]=document.form__test.event_true;
    ar[1]=document.form__test.event_random;
    ar[2]=document.form__test.event_interesting;
    let count=0;
   for(let i=0;i<3; i++)
   {
       if (ar[i].checked===true)
           count++;
   }
   if (count<2)
   {
       alert ( "Пожалуйста, выберите 2 или более вариантов ответа в третьем вопросе" );
       valid = false
   }
    return valid;
}
