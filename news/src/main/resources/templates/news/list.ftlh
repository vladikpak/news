<#import "../layout/main.ftlh" as main>
<@main.layout "Просмотр новостей">

    <h2>Новости</h2>

    <div class="row">
        <div class="col-md-3">
            <div class="list-group">
                <a href="/news" class="list-group-item list-group-item-action
                <#if !filters?? || filters == ''>active</#if>">
                    Все новости
                </a>
                <a href="/news?filter=published" class="list-group-item list-group-item-action
                <#if filters?? && filters == 'published'>active</#if>">
                    ✅ Опубликованные
                </a>
                <a href="/news?filter=drafts" class="list-group-item list-group-item-action
                <#if filters?? && filters == 'drafts'>active</#if>">
                    📝 Черновики
                </a>
                <a href="/news?filter=archive" class="list-group-item list-group-item-action
                <#if filters?? && filters == 'archive'>active</#if>">
                    🗄️ Архив
                </a>
                <a href="/news?filter=pinned" class="list-group-item list-group-item-action
                <#if filters?? && filters == 'pinned'>active</#if>">
                    📌 Закрепленные
                </a>
            </div>
        </div>
    </div>

        <!-- Центр: список новостей -->
        <div class="col-md-6">
            <#if news?? && news?has_content>
                <div class="row">
                    <#list news as item>
                        <div class="col-md-12 mb-4">
                            <div class="card h-100 ${item.pinned?string('border-danger', '')}">

                                <!-- Картинка -->
                                <#if item.imagePath?? && item.imagePath != "">
                                    <img src="${item.imagePath}" class="card-img-top" style="height: 200px; object-fit: cover;">
                                <#else>
                                    <img src="/images/default.jpg" class="card-img-top" style="height: 200px; object-fit: cover;">
                                </#if>

                                <div class="card-body">
                                    <!-- Бейджи статусов -->
                                    <div class="mb-2">
                                        <#if item.pinned>
                                            <span class="badge bg-danger">📌 ЗАКРЕПЛЕНО</span>
                                        </#if>

                                        <#if !item.published && !item.archived>
                                            <span class="badge bg-warning text-dark">📝 ЧЕРНОВИК</span>
                                        </#if>

                                        <#if item.published && !item.archived>
                                            <span class="badge bg-success">✅ ОПУБЛИКОВАНО</span>
                                        </#if>

                                        <#if item.archived>
                                            <span class="badge bg-secondary">🗄️ В АРХИВЕ</span>
                                        </#if>
                                    </div>

                                    <h5 class="card-title">${item.title!""}</h5>
                                    <p class="card-text">${item.description!""}</p>
                                    <p class="card-text"><small class="text-muted">${item.description2!""}</small></p>

                                    <!-- Кнопки действий -->
                                    <div class="mt-3">
                                        <!-- Опубликовать / В черновик -->
                                        <#if !item.archived>
                                            <form action="/news/${item.id}/togglePublished" method="post" style="display: inline;">
                                                <@main.csrf />
                                                <#if item.published>
                                                    <button class="btn btn-warning btn-sm">✏️ В черновик</button>
                                                <#else>
                                                    <button class="btn btn-success btn-sm">📢 Опубликовать</button>
                                                </#if>
                                            </form>
                                        </#if>

                                        <!-- Закрепить / Открепить -->
                                        <#if !item.archived && item.published>
                                            <form action="/news/${item.id}/togglePinned" method="post" style="display: inline;">
                                                <@main.csrf />
                                                <#if item.pinned>
                                                    <button class="btn btn-secondary btn-sm">📍 Открепить</button>
                                                <#else>
                                                    <button class="btn btn-danger btn-sm">📌 Закрепить</button>
                                                </#if>
                                            </form>
                                        </#if>

                                        <!-- В архив / Восстановить -->
                                        <form action="/news/${item.id}/toggleArchive" method="post" style="display: inline;">
                                            <@main.csrf />
                                            <#if item.archived>
                                                <button class="btn btn-success btn-sm">📂 Восстановить</button>
                                            <#else>
                                                <button class="btn btn-secondary btn-sm">🗄️ В архив</button>
                                            </#if>
                                        </form>

                                        <a href="/news/${item.id}/edit" class="btn btn-primary btn-sm">✏️ Ред.</a>
                                        <a href="/news/${item.id}" class="btn btn-info btn-sm">👁️ Смотреть</a>
                                    </div>
                                </div>

                                <div class="card-footer text-muted">
                                    <#if item.publishDate??>
                                        📅 ${item.publishDate}
                                    </#if>
                                </div>
                            </div>
                        </div>
                    </#list>
                </div>
            <#else>
                <div class="alert alert-info text-center">
                    📭 Новостей пока нет
                    <a href="/news/create" class="alert-link">Создать первую новость</a>
                </div>
            </#if>
        </div>

        <!-- Правый сайдбар -->
        <div class="col-md-3">
            <div class="list-group">
                <a class="list-group-item list-group-item-action active">Категории</a>
                <a class="list-group-item list-group-item-action">События</a>
                <a class="list-group-item list-group-item-action">Акции</a>
            </div>

            <div class="mt-3">
                <a href="/news/create" class="btn btn-success w-100">➕ Создать новость</a>
            </div>
        </div>
    </div>

</@main.layout>