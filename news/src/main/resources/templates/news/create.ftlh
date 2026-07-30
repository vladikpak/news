<#import "../layout/main.ftlh" as main>
<@main.layout "Создание новостей">

<form action="/news/create" method="post" enctype="multipart/form-data">
    <@main.csrf />

    <div class="mb-3">
        <label>Заголовка</label>
        <input type="text" name="title" class="form-control" required>
    </div>
    <div class="mb-3">
        <label>Описание</label>
        <input type="text" name="description" class="form-control" required>
    </div>
    <div class="mb-3">
        <label>Описание</label>
        <input type="text" name="description2" class="form-control" required>
    </div>
    <div class="mb-3">
        <label>Обложка (картинка)</label>
        <input type="file" name="photo" class="form-control" accept="image/*">
    </div>

    <button type="submit" class="btn btn-primary">Сохранить</button>
    <a href="/news" class="btn btn-secondary">Отмена</a>
</form>

</@main.layout>