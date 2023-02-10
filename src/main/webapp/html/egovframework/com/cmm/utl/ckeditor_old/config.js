/**
 * @license Copyright (c) 2003-2019, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function( config ) {
    // Define changes to default configuration here.
    // For complete reference see:
    // https://ckeditor.com/docs/ckeditor4/latest/api/CKEDITOR_config.html
    config.filebrowserImageUploadUrl = makeApiUrl('/common/ckeditorImageUpload.do?');
    config.width ='100%';
    config.height = '400px';
    config.htmlEncodeOutput = false;

    // The toolbar groups arrangement, optimized for two toolbar rows.
    /*config.toolbarGroups = [
        { name: 'clipboard',   groups: [ 'clipboard', 'undo' ] },
        { name: 'insert' },
        { name: 'document',    groups: [ 'mode', 'document', 'doctools' ] },
        { name: 'others' },
        '/',
        { name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
        { name: 'styles' },
        { name: 'colors' },
    ];*/
    config.toolbarGroups = [
        { name: 'clipboard', groups: [ 'clipboard', 'undo' ] },
        { name: 'insert', groups: [ 'insert' ] },
        { name: 'document', groups: [ 'mode', 'document', 'doctools' ] },
        '/',
        { name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
        { name: 'styles', groups: [ 'styles' ] },
        { name: 'colors', groups: [ 'colors' ] },
        { name: 'links', groups: [ 'links' ] },
        { name: 'editing', groups: [ 'find', 'selection', 'spellchecker', 'editing' ] },
        { name: 'forms', groups: [ 'forms' ] },
        { name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi', 'paragraph' ] },
        '/',
        { name: 'tools', groups: [ 'tools' ] },
        { name: 'others', groups: [ 'others' ] },
        { name: 'about', groups: [ 'about' ] }
    ];

    config.removeButtons = 'Print,Templates,PasteFromWord,SelectAll,Form,Checkbox,Radio,TextField,Textarea,Select,Button,ImageButton,HiddenField,CreateDiv,BidiLtr,BidiRtl,Anchor,Smiley,SpecialChar,PageBreak,Iframe,Replace,Find,Scayt,CopyFormatting,NumberedList,RemoveFormat,Underline,Subscript,Superscript,BulletedList,Indent,Outdent,Blockquote,JustifyLeft,JustifyCenter,Maximize,JustifyRight,Language,JustifyBlock,ShowBlocks,About,ExportPdf,Flash';

    // Remove some buttons provided by the standard plugins, which are
    // not needed in the Standard(s) toolbar.

    config.extraPlugins = 'colorbutton,font,justify';
    //config.removeButtons = 'Underline,Subscript,Superscript';
    config.entities = false;
    // Set the most common block elements.
    config.format_tags = 'p;h1;h2;h3;pre';

    // Simplify the dialog windows.
    config.removeDialogTabs = 'image:advanced;link:advanced';

    // p태그 제거
    //config.enterMode = CKEDITOR.ENTER_BR;

    CKEDITOR.on('dialogDefinition', function( ev ){
        var dialogName = ev.data.name;
        var dialogDefinition = ev.data.definition;

        switch (dialogName) {
            case 'image':
                dialogDefinition.removeContents('Link');
                dialogDefinition.removeContents('advanced');
                break;
        }
    });
};
