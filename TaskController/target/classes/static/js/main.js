$(function(){

    const appendTask = function(data){
        var taskCode = '<a href="#" class="task-link" data-id="' +
        data.id + '">' + data.name + '</a><br>';
        $('#task-list')
            .append('<div>' + taskCode + '</div');
    };

    $('#show-add-task-form').click(function(){
         $('#task-form').css('display', 'flex');
    });
    //close adding form
    $('#task-form').click(function(event){
            if(event.target === this) {
                $(this).css('display', 'none');
            }
    });

    //show update form
    $('#show-update-task-form').click(function(){
          $('#update-task-form').css('display', 'flex');
    });
    //close update form
    $('#update-task-form').click(function(event){
            if(event.target === this) {
                $(this).css('display', 'none');
            }
    });
    //show remove form
    $('#show-remove-task-form').click(function(){
          $('#remove-task-form').css('display', 'flex');
    });
    //close remove form
    $('#remove-task-form').click(function(event){
            if(event.target === this) {
                 $(this).css('display', 'none');
            }
    });

    //get task
    $(document).on('click', '.task-link', function(){
        var link = $(this);
        var taskId = link.data('id');
        $.ajax({
            method: "GET",
            url: '/tasks/' + taskId,
            success: function(response)
            {
                var code = '<span>Дата окончания: ' + response.date + '<br>Описание: ' + response.description + '</span>'
                link.parent().append(code);
            },
            error: function(response)
            {
                if(response.status == 404) {
                    alert('Задание не найдено!');
                }
            }
        });
        return false;
    });

    //add task
    $('#save-task').click(function(){
        var data = $('#task-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/tasks/',
            data: data,
            success: function(response)
            {
                $('#task-form').css('display', 'none');
                var task = {};
                task.id = response;
                var dataArray = $('#task-form form').serializeArray();
                for(i in dataArray) {
                    task[dataArray[i]['name']] = dataArray[i]['value'];
                }
            alert('Задание ' + task.id + ' добавлено');
            appendTask(task);
            }
        });
        return false;
    });

    //put task
    $('#update-task').click(function(){
        var data = $('#update-task-form form').serialize();
        taskId = $('#task-id').val();
        $.ajax({
            method: "PUT",
            url: 'tasks/' + taskId,
            data: data,
            success: function(response)
            {
                $('#update-task-form').css('display', 'none');
                var task = $(this);
                var dataArray = $('#update-task-form form').serializeArray();
                for(i in dataArray) {
                    task[dataArray[i]['name']] = dataArray[i]['value'];
                }
                alert('Задание ' + taskId + ' изменено');
                location.reload();
            },
            error: function(response)
            {
                if(response.status == 404) {
                     alert('Задание не найдено!');
                }
            }
        });
        return false;
    });

    //delete task
    $('#remove-task').click(function(){
        var taskId = $('#id').val();
        $.ajax({
            method: "DELETE",
            url: 'tasks/' + taskId,
            success: function(response)
            {
                $('#remove-task-form').css('display', 'none');
                alert('Задание ' + taskId + ' удалено');
                location.reload();
            },
            error: function(response)
            {
                if(response.status == 404) {
                    alert('Задание не найдено!');
                }
            }
        });
        return false;
    });

    //remove all
    $('#remove-all').click(function(){
        $.ajax({
            method: "DELETE",
            url: '/tasks/',
            success: function(response)
            {
                $('#task-list').remove();
                alert('Все задания удалены');
            },
            error: function(response)
            {
                if(response.status == 404) {
                     alert('Задание не найдено!');
                }
            }
        });
        return false;
    });
});