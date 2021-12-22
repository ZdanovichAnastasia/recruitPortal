function addField () {
    var telnum = parseInt($("#form_add_skills").find("div.add:last").attr("id").slice(3))+1;
    $("div#form_add_skills").append('<div id="add'+telnum+'" class="col-sm-10  add"><input type="text" placeholder="Навык" name="skills[]" id="skills"  value="" style="width: 90%; display: inline"/><span title="удалить поле" class="fa fa-remove" onclick="deleteField('+telnum+');" style=" float: right;color: #333; background-color: #d4d4d4; border-color: #8c8c8c; padding: 5px 10px; font-size: 16px; line-height: 1.5; border-radius: 3px;"></span></div>');
}
//Функция удаления поля
function deleteField (id) {
    $('div#add'+id).remove();
    writeFieldsVlues();
}