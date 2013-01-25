====================================================================
Mimetype icons, mimetype css class and sprited version of this icons
====================================================================

Project forked from https://github.com/firejune/mime

In your project, import "mime.png" and "mime-sprite.min.css" file.

In your html file :

::

    <link rel="stylesheet" href="mime-sprite.css" type="text/css" media="screen" charset="utf-8" />

In your content, you can use icon like this :

::
    
    <span class="text_html"></span>


Rebuild sprite image and css file
=================================

Install Python dependency packages :

::

    $ pip install spritemapper cssmin


Generate sprite icon file :

::

    $ spritemapper mime.css

Minimize css file :

::

    $ cat mime-sprite.css | cssmin > mime-sprite.min.css 

This is the list of files result :

::

   $ tree -L 1
   .
   ├── mime
   ├── mime.css
   ├── mime.png
   ├── mime-sprite.css
   ├── mime-sprite.min.css
   └── README.md

   1 directory, 5 files 

Get mimetype class name from mimetype in Python :

::

    mimetype.replace("/", "_").replace(".", "-")

Get mimetype class name from mimetype in Javascript :

::

    mimetype.replace("/", "_").replace(".", "-")

Contact
=======

Home page : http://stephane-klein.info
Mail : Stéphane Klein <contact@stephane-klein.info>
