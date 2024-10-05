const signUpBasdsad = document.getElementById('signUp');
const signInBasdasdasd = document.getElementById('signIn');
const hello = 1;
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
	container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
	container.classList.remove("right-panel-active");
});

// Switch sign up và sign in trên mobile
document.addEventListener('DOMContentLoaded', function () {
    const signUpLink = document.getElementById('signUpUP');
    const signInLink = document.getElementById('signInUP');
    const registerForm = document.querySelector('.register-form');
    const loginForm = document.querySelector('.login-form');

    // Hiển thị mặc định form đăng nhập
    loginForm.style.display = 'block';

    signUpLink.addEventListener('click', function (e) {
        e.preventDefault();
        registerForm.style.display = 'block';
        loginForm.style.display = 'none';
    });

    signInLink.addEventListener('click', function (e) {
        e.preventDefault();
        loginForm.style.display = 'block';
        registerForm.style.display = 'none';
    });
});
