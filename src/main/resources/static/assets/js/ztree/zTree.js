// 树形数据展示
;(function ($) {
    let self;
    const TableTree = function (param) {
        self = this;
        this.defaults = {
            tree: $(".timo-tree"),
            treeTable: ".timo-tree-table",   // 表格类名
            treeIcon: "<i class='toggle-icon fa fa-chevron-right'></i>",
            treeFill: "<i class='toggle-fill'></i>",
            hideRank: 3,
            oldActive: null,
            oldButton: null,
            scrollTop: 90,
        }
        this.options = $.extend({}, this.defaults, param);
    };

    TableTree.prototype = {
        // 初始化
        init: function () {
            // 获取树形列表数据
            const tree = self.options.tree;
            $.get(tree.data('url'), function (result) {
                if (result.data.length > 0) {
                    // zTree传递列表数据
                    self.zTreeReady(result.data);
                    // 树形表格传递列表数据
                    self.tableTree(result.data);
                    // 开启树形表格子级开关
                    self.toggleChild();
                }
            });
        },

        // 操作zTree组件
        zTreeReady: function (listData) {
            const setting = {
                view: {
                    addHoverDom: addHoverDom,
                    removeHoverDom: removeHoverDom,
                },
                edit: {
                    enable: true,
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                callback: {
                    onClick: onClick,
                    onExpand: onExpand,
                    onCollapse: onCollapse,
                    beforeEditName: beforeEditName,
                    beforeRemove: beforeRemove,
                }
            };

            function onClick(event, treeId, treeNode, clickFlag) {
                const tNode = $("[tree-id='" + treeNode.id + "']");
                if (self.options.treeActive != null) {
                    self.options.treeActive.removeClass("tree-active");
                }
                self.options.treeActive = tNode;
                tNode.addClass("tree-active");
                $(document).scrollTop(tNode.offset().top - self.options.scrollTop);

            }

            function onExpand(event, treeId, treeNode) {
                const tNode = $("[tree-id='" + treeNode.id + "']");
                self.expandChild(tNode, true);
            }

            function onCollapse(event, treeId, treeNode) {
                const tNode = $("[tree-id='" + treeNode.id + "']");
                self.expandChild(tNode, false);
            }

            function addHoverDom(treeId, treeNode) {
                const node = $("#" + treeNode.tId + "_span");
                if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
                const addNode = "<span class='button tree-add' id='addBtn_" + treeNode.tId + "'></span>";
                node.after(addNode);
                const btn = $("#addBtn_" + treeNode.tId);
                if (btn) btn.bind("click", function () {
                    const popup = $(".popup-add");
                    const url = popup.data('url');
                    popup.attr("data-url", url + "/" + treeNode.id);
                    popup.click();
                    popup.attr("data-url", url);
                    return false;
                });
            }

            function removeHoverDom(treeId, treeNode) {
                $("#addBtn_" + treeNode.tId).unbind().remove();
            }

            function beforeEditName(treeId, treeNode) {
                const trNode = $("[tree-id='" + treeNode.id + "']");
                const edit = trNode.find(".popup-edit");
                edit.click();
                return false;
            }

            function beforeRemove(treeId, treeNode) {
                const trNode = $("[tree-id='" + treeNode.id + "']");
                const del = trNode.find(".popup-delete");
                del.click();
                return false;
            }

            // 封装zTree数据
            const zNodes = [];
            listData.forEach(function (val) {
                const nav = {
                    id: val.id,
                    pId: val.pid,
                    name: val.title
                };
                if (nav.pId === 0) {
                    nav.isParent = true;
                    nav.open = true;
                }
                zNodes.push(nav);
            });

            // zTree组件初始化
            $(document).ready(function () {
                $.fn.zTree.init(self.options.tree.find(".ztree"), setting, zNodes);
            });
        },

        // 封装树形表格
        tableTree: function (listData) {
            // 封装树形结构数据
            const newList = [];
            const treeList = [];

            listData.forEach(function (item) {
                newList[item.id] = item;
            });

            listData.forEach(function (item) {
                if (newList[item.pid] !== undefined) {
                    if (newList[item.pid].children === undefined) {
                        newList[item.pid].children = [];
                    }
                    newList[item.pid].children.push(item);
                } else {
                    treeList.push(item);
                }
            });

            // 获取表格展示模型
            const tbody = $(self.options.treeTable + " tbody");
            const template = tbody.html();
            tbody.empty();
            tbody.css("visibility", "visible");

            // 填充数据
            const regex = /\{\{([$A-Za-z]+?)\}\}/g;
            const rank = 1;
            self.expandTree(treeList, rank, function (item, rank) {
                const callback = template.replace(regex, function ($1) {
                    const point = $1.substring(2, $1.length - 2);
                    if (point === "title") {
                        let icon = self.options.treeIcon;
                        if (item.children === undefined) {
                            icon = self.options.treeFill;
                        }
                        let fill = "";
                        for (let i = 0; i < rank - 1; i++) {
                            fill += self.options.treeFill;
                        }
                        return fill + icon + item[point];
                    } else if (point === '$hide') {
                        const isHide = (rank >= self.options.hideRank);
                        return isHide ? "tree-hidd" : "";
                    } else {
                        return item[point];
                    }
                });
                tbody.append(callback);
            });
        },

        // 展开树形数据
        expandTree: function (list, rank, callback) {
            list.forEach(function (item) {
                callback(item, rank);
                if (item.children !== undefined) {
                    self.expandTree(item.children, ++rank, callback);
                    rank -= 1;
                }
            });
        },

        // 树形表格子级开关
        toggleChild: function () {
            $(".toggle-icon").click(function () {
                const trNode = $(this).parents("tr");
                const id = trNode.attr("tree-id");
                const childs = $("[tree-pid='" + id + "']");
                const isClose = childs.hasClass("tree-hidd");
                self.expandChild(trNode, isClose);
            });
        },

        // 递归所有子级开关
        expandChild: function (trNode, isClose) {
            const id = trNode.attr("tree-id");
            const childs = $("[tree-pid='" + id + "']");
            if (!isClose) {
                childs.addClass("tree-hidd");
                childs.each(function (key, item) {
                    self.expandChild($(item), isClose);
                });
            } else {
                childs.removeClass("tree-hidd");
            }
        }
    }

    $.fn.aceTree = function (param) {
        const tableTree = new TableTree(param);
        return tableTree.init();
    }
})(jQuery);

// 树形选择器
(function ($) {
    let self;
    const SelectTree = function (param) {
        self = this;
        this.defaults = {
            tree: $(".select-tree"),
            rootTree: null,
            onSelected: function () {
            }
        }
        this.options = $.extend({}, this.defaults, param);
    };

    SelectTree.prototype = {
        // 初始化
        init: function () {
            // 获取树形列表数据
            const tree = self.options.tree;
            // 构造悬浮选择器
            self.selector();
            // 重构选择框
            self.resetSelect(tree);
            // 点击时显示悬浮选择器
            tree.click(function () {
                const node = $(this);
                $.get(node.data('url'), function (result) {
                    //if(result.data.length > 0){
                    // 显示定位悬浮选择器
                    self.position(node);
                    // zTree传递列表数据
                    self.zTreeReady(result.data, node);
                    //}
                });
            });
        },

        // 操作zTree组件
        zTreeReady: function (listData, node) {
            const setting = {
                view: {
                    dblClickExpand: false
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                callback: {
                    onClick: function (event, treeId, treeNode) {
                        node.val(treeNode.name);
                        node.siblings("[type='hidden']").val(treeNode.id);
                        $(".selectContent").hide();
                        self.options.onSelected(treeNode);
                    }
                }
            };

            // 封装zTree数据
            const zNodes = [];
            if (self.options.rootTree != null) {
                zNodes.push({id: 0, name: self.options.rootTree, open: true});
            }
            listData.forEach(function (val) {
                const nav = {
                    id: val.id,
                    pId: val.pid,
                    name: val.title
                };
                if (nav.pId == 0) {
                    nav.isParent = true;
                    nav.open = true;
                }
                zNodes.push(nav);
            });

            $(document).ready(function () {
                $.fn.zTree.init($(".selectContent>.ztree"), setting, zNodes);
            });
        },

        // 构造悬浮选择器
        selector: function () {
            $("body").append("\n" +
                "<div class='selectContent'>" +
                "    <ul class='ztree'></ul>" +
                "</div>");
        },

        // 重构选择框
        resetSelect: function (tree) {
            tree.each(function (key, item) {
                const name = $(item).attr("name");
                const value = $(item).data("value");
                $(item).removeAttr("name");
                $(item).attr("readonly", true);
                const input = $("<input name='" + name + "' type='hidden'>");
                if (value != undefined) input.val(value);
                $(item).after(input);
                $(item).after("<i class='layui-edge'></i>");
            });
        },

        // 显示定位悬浮选择器
        position: function (tree) {
            const source = self.options.tree;
            const offset = tree.offset();
            $(".selectContent").css({
                top: offset.top + tree.outerHeight() + 'px',
                left: offset.left + 'px',
                width: source.innerWidth()
            }).show();

            $("body").bind("click", function (e) {
                const target = $(e.target).parents(".selectContent");
                if (!target.length > 0) {
                    $(".selectContent").hide();
                }
            });
        },
    }

    $.fn.selectTree = function (param) {
        const selectTree = new SelectTree(param);
        return selectTree.init();
    }
})(jQuery);
