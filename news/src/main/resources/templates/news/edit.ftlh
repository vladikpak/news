<#import "../layout/main.ftlh" as main>
<@main.layout "Редактирование новостей">

    <form action="/news/${news.id}/edit" method="post" enctype="multipart/form-data">
        <@main.csrf />

        <div class="mb-3">
            <label>Заголовка</label>
            <input type="text" name="title" class="form-control" value="${news.title}" required>
        </div>
        <div class="mb-3">
            <label>Описание</label>
            <input type="text" name="description" class="form-control" value="${news.description}" required>
        </div>
        <div class="mb-3">
            <label>Описание</label>
            <input type="text" name="description2" class="form-control" value="${news.description2}" required>
        </div>

        <div class="mb-3">
            <label>Новое изображение (оставьте пустым, если не хотите менять)</label>
            <input type="file" name="imageFile" class="form-control" accept="image/*">
            <small class="text-muted">Текущее изображение: ${news.imagePath!""}</small>
        </div>

        <button type="submit" class="btn btn-primary">Сохранить</button>
        <a href="/news" class="btn btn-secondary">Отмена</a>
    </form>

</@main.layout>