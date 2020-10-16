<!DOCTYPE html>
<html>
    <head>
        <meta   charset="utf-8">
        <title>新增学院信息</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="/css/bootstrap.min.css">
        <script src="/js/jquery.min.js"></script>
        <script src="/js/bootstrap.min.js"></script>
        <script src="/js/jquery.cookie.min.js"></script>
    </head>
    <body>
        <div    class="container">
            <h5 style="margin-top: 50px; text-align: center;">新增学院信息</h5>
            <div    class="row">
                <div    class="col-sm-8 input-group input-group-lg"   style="margin-top: 20px;">
                    <input  type="text" class="form-control"    placeholder="信息名">
                </div>
                <div    class="col-sm-2"    style="margin-top: 20px;">
                    <button type="button"   class="btn btn-outline-info btn-lg">保存</button>
                </div>
            </div>
            <!--使用富文本编辑器wangEditor-->
            <div    id="wangE"  style="margin-top: 20px;"></div>
            <script type="text/javascript" src="/js/wangEditor.min.js"></script>
            <script type="text/javascript">
                const   E = window.wangEditor
                const editor = new E( document.getElementById('wangE') )
                editor.config.height = 400      /*设置编辑器的高*/
                editor.create()
            </script>
        </div>
    </body>
</html>