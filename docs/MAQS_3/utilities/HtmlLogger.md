# <img src="resources/MAQS.jpg" height="32" width="32"> HTML Logger

```html
<!DOCTYPE html>
<html lang='en'>
<head>
    <meta charset='utf-8'>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <title></title>
    <link rel='stylesheet'
          href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css'>
    <script src='https://code.jquery.com/jquery-3.4.1.slim.min.js'
            integrity='sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n'
            crossorigin='anonymous'></script>
    <script src='https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js'
            integrity='sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo'
            crossorigin='anonymous'></script>
    <script src='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js'
            integrity='sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6'
            crossorigin='anonymous'></script>
    <script src='https://use.fontawesome.com/releases/v5.0.8/js/all.js'></script>
</head>
<body>
<style>Report Name</style>
<script>
    $(function (){$('.pop').on('click', function (e){$('.imagepreview').attr('src', $(this).find('img').attr('src'));$('#imagemodal').modal('show');});});

</script>
<script>
    $(function (){$('.pop2').on('click', function (){$('.imagepreview').attr('src', $(this).attr('src'));$('#imagemodal').modal('show');});});

</script>
<script>
    $(function (){$('.dropdown-item').on('click', function (e){$(this).attr('class', function (i, old){return old=='dropdown-item' ? 'dropdown-item bg-secondary' :'dropdown-item';});var temp=$(this).data('name');$('[data-logtype=\\\'' + temp + '\\\']').toggleClass('show');e.stopPropagation();});});

</script>
<script>
    $(function (){$(document).ready(function(){$('#Header').append(' ' + $('title').text())})});

</script>
<script>
    $(function(){$(document).ready(function(){$('.card-text:contains(\\\'Test\\\')').each(function (index, element){switch($(this).text()){case 'Test passed': $('#TestResult:not([class])').addClass('text-success'); case 'Test failed': $('#TestResult:not([class])').addClass('text-danger'); case 'Test was inconclusive': $('#TestResult:not([class])').addClass('text-danger'); $('#TestResult').text($(this).text()); $('#TestResult').addClass('font-weight-bold'); break;}})})})

</script>
<div id='Header' class='dropdown'>
    <button class='btn btn-secondary dropdown-toggle'
            type='button'
            id='FilterByDropdown'
            data-toggle='dropdown'
            aria-haspopup='true'
            aria-expanded='false'>Filter By
    </button>
    <div class='dropdown-menu' aria-labelledby='FilterByDropdown'>
        <button class='dropdown-item' data-name='ERROR'>Filter Error</button>
        <button class='dropdown-item' data-name='WARNING'>Filter Warning</button>
        <button class='dropdown-item' data-name='SUCCESS'>Filter Success</button>
        <button class='dropdown-item' data-name='GENERIC'>Filter Generic</button>
        <button class='dropdown-item' data-name='STEP'>Filter Step</button>
        <button class='dropdown-item' data-name='ACTION'>Filter Action</button>
        <button class='dropdown-item' data-name='INFORMATION'>Filter Information</button>
        <button class='dropdown-item' data-name='VERBOSE'>Filter Verbose</button>
        <button class='dropdown-item' data-name='IMAGE'>Filter Images</button>
    </div>
    <span id='TestResult'></span>
</div>
<div class='container-fluid'>
    <div class='row'>
        <div class='collapse col-12 show' data-logtype='WARNING'>
            <div class='card'>
                <div class='card-body text-warning'>
                    <h5 class='card-title mb-1'>WARNING</h5>
                    <h6 class='card-subtitle mb-1'>2022-06-29 09:42:55</h6>
                    <p class='card-text'>
                        This is a test.</p></div>
            </div>
        </div>
        <div class='collapse col-12 show' data-logtype='WARNING'>
            <div class='card'>
                <div class='card-body text-warning'>
                    <h5 class='card-title mb-1'>WARNING</h5>
                    <h6 class='card-subtitle mb-1'>2022-06-29 09:42:55</h6>
                    <p class='card-text'>
                        This is a test to write to an existing file.</p></div>
            </div>
        </div>
        <div class='modal fade' id='imagemodal' tabindex='-1' role='dialog'
             aria-labelledby='myModalLabel'
             aria-hidden='true'>
            <div class='modal-dialog'>
                <div class='modal-content'>
                    <div class='modal-body'>
                        <button type='button' class='close' data-dismiss='modal'><span
                                aria-hidden='true'>&times;</span><span
                                class='sr-only'>Close</span></button>
                        <img src='' alt="" class='imagepreview' style='width: 100%;'></div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
```
