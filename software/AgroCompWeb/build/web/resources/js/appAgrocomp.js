var carregaImagem = function (event) {
                var output = document.getElementById('output');
                output.src = URL.createObjectURL(event.target.files[0]);
            };
            
function SomenteNumero(e) {
                var tecla = (window.event) ? event.keyCode : e.which;
                if ((tecla > 47 && tecla < 58 || tecla==46))
                    return true;
                else {
                    if (tecla == 8 || tecla == 0)
                        return true;
                    else
                        return false;
                }
            }

$(document).ready(function () {
    $('.parallax').parallax();
});

  $(document).ready(function() {
    $('input#input_text, textarea#textarea1').characterCounter();
  });

$(document).ready(function () {
    $('ul.tabs').tabs();
});

$(document).ready(function () {
    $('.slider').slider({full_width: true});
});
$(document).ready(function () {
    $('select').material_select();
});
$(document).ready(function () {
    $(".button-collapse").sideNav();
});

$(document).ready(function(){
    // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
    $('.modal-trigger').leanModal();
  });
  
  

        