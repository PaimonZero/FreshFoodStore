// open - close search in header mobile
document.querySelector('.search-btn').addEventListener('click', function () {
    event.preventDefault();
    this.parentElement.classList.toggle('open')
    this.previousElementSibling.focus()
})