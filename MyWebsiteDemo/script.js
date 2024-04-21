const wrapper=document.querySelector('.wrapper');
const loginLink=document.querySelector('.Login-Link');
const RegisterLink=document.querySelector('.Register-Link');

RegisterLink.addEventListener('click',()=>

{
    wrapper.classList.add('active');
}
);
loginLink.addEventListener('click',()=>
{
    wrapper.classList.remove('active');
});
