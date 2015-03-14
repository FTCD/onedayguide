$(document).ready(function(){
   $.post("LangManager", {"opt": "getLang"}, function(data){ chgLang(data); });
});

function chgLang(lang){

   jQuery.i18n.properties({

       name:'locale', 
       path:'js/locales/', 
       mode:'both',
       language:lang, 
       callback: function() {
          updateDomText();
          $.post("LangManager", {"opt": "setLang", "lang": lang});
       }

   });

}   

function updateDomText(){

   $(".i18n").each(function(i, element){

      if(element.tagName == "input")
         $(element).val(jQuery.i18n.prop(element.id));
      else
         $(element).html(jQuery.i18n.prop(element.id));

   });

}

function validateRegister(){

   /*$.post("AuthManager", {"user": $("#txtUser").val(), "pwd": $("#txtPassword").val()}, function(data){
      var msg;
      var color;
      if(data == "0"){
         msg = jQuery.i18n.prop("login.successMsg", [$("#txtUser").val()]);
         color = "green";
      }
      else{
         msg = jQuery.i18n.prop("login.failMsg");
         color = "red";
      }
      $("#loginForm>#divMsg").remove();
      $("#loginForm").append($("<div>").attr("id", "divMsg").html(msg).css("color", color));
   });*/

}