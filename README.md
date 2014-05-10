BungeeJapanizeMessenger
========================

BungeeCord を利用して複数サーバーを連結している環境において、サーバーをまたいだ tellコマンドの送信を可能にします。
また、メッセージの内容がローマ字表記の場合は、漢字に変換して表示することが可能です。

<img src="https://github.com/ucchyocean/BungeeJapanizeMessenger/blob/master/release/bjm.png?raw=true" alt="スクリーンショット" width=427 height=240></img>


※ 使用方法：<b>BungeeCordのpluginsフォルダ</b>に、jarファイルを入れてください。<br/>
<b>Bukkitのpluginsフォルダではありません！</b>

※ コンフィグ：plugins/BungeeJapanizeMessenger/config.yml に生成されます。
<pre>
# BungeeJapanizeMessenger v0.0.1
# @author     ucchy
# @license    LGPLv3
# @copyright  Copyright ucchy 2013

# /tell や /msg や /r コマンドでプライベートメッセージを送信するときに、
# 適用されるフォーマット設定。
defaultFormatForPrivateMessage: '&7[%sender@%senderserver > %reciever@%recieverserver] %msg'

# ローマ字をかな文字や漢字に変換する設定。
# none/kana/GoogleIME/SocialIME の4つのいずれかが設定可能。
# none      : 変換なし。
# kana      : かな変換のみ。
# GoogleIME : かな変換したのち、GoogleIMEを使って漢字変換する。
# SocialIME : かな変換したのち、SocialIMEを使って漢字変換する。
japanizeType: GoogleIME

# Japanize変換されたメッセージ部分の、表示フォーマット設定。
japanizeLine1Format: '%msg &7(%japanize)'
</pre>

※ ライセンス：LGPLv3を適用します。ソースコードを流用する場合は、流用先にもLGPLv3を適用してください。

※ 何かあったときの連絡先：<a href="https://twitter.com/ucchy99">ツイッター</a>へ連絡ください。

※ ダウンロード：<br/>
https://github.com/ucchyocean/BungeeJapanizeMessenger/blob/master/release/BungeeJapanizeMessenger.zip?raw=true
