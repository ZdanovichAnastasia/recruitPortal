var headerMainText = '<div class="container">\n' +
    '            <ul class="logo">\n' +
    '                <li>\n' +
    '                    <a href="/">\n' +
    '                        <img src="/images/logo.png"></a>\n' +
    '                </li>\n' +
    '            </ul>\n' +
    '            </ul>' +
    '                <ul class="login">\n' +
    '                <li>\n' +
    '                    <a href="/logIn" class="btn_auto">Войти</a>\n' +
    '                </li>\n' +
    '            </ul>\n' +
    '        </div>';
var headerAdminText = '<div class="container">\n' +
    '            <ul class="logo">\n' +
    '                <li>\n' +
    '                    <a href="/">\n' +
    '                        <img src="/images/logo.png"></a>\n' +
    '                </li>\n' +
    '            </ul>\n' +
    '            <ul class="main-menu">\n' +
    '                <li><a href="/admin/showAllUsers">Рекрутеры</a></li>\n' +
    '                <li><a href="/">Вакансии</a></li>\n' +
    '            </ul>' +
    '        </div>';

var headerRecruiterText = '<div class="container">\n' +
    '            <ul class="logo">\n' +
    '                <li>\n' +
    '                    <a href="/">\n' +
    '                        <img src="/images/logo.png"></a>\n' +
    '                </li>\n' +
    '            </ul>\n' +
    '            <ul class="main-menu">\n' +
    '                <li> <a href="/recruiter/recruiterProfile">Профиль</a></li>\n' +
    '                <li><a href="/recruiter/showRecruiterJobPosts">Вакансии</a></li>\n' +
    '                <li><a href="/recruiter/showResponses">Отклики</a></li>\n' +
    '            </ul>' +
    '        </div>';

function setHeader(role){
    var header =  document.createElement("header");
    if(role == 'администратор'){
        header.innerHTML = headerAdminText ;
    }
    else if(role == 'рекрутер'){
        header.innerHTML = headerRecruiterText;
    }
    else{
        header.innerHTML = headerMainText ;
    }
    document.body.insertAdjacentElement('afterbegin', header );
}