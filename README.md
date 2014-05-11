BungeeJapanizeMessenger
========================

BungeeCord を利用して複数サーバーを連結している環境において、サーバーをまたいだ tellコマンドの送信を可能にします。
また、メッセージの内容がローマ字表記の場合は、漢字に変換して表示することが可能です。

<img src="https://github.com/ucchyocean/BungeeJapanizeMessenger/blob/master/release/bjm.png?raw=true" alt="スクリーンショット" width=427 height=240></img>


※ 使用方法：<b>BungeeCordのpluginsフォルダ</b>に、jarファイルを入れてください。<br/>
<b>Bukkitのpluginsフォルダではありません！</b>

※ コンフィグ：plugins/BungeeJapanizeMessenger/config.yml に生成されます。
<pre>
# BungeeJapanizeMessenger v0.0.2
# @author     ucchy
# @license    LGPLv3
# @copyright  Copyright ucchy 2014


# -------------------- Japanize変換設定 --------------------

# ローマ字をかな文字や漢字に変換する設定。
# none/kana/GoogleIME/SocialIME の4つのいずれかが設定可能。
# none      : 変換なし。
# kana      : かな変換のみ。
# GoogleIME : かな変換したのち、GoogleIMEを使って漢字変換する。
# SocialIME : かな変換したのち、SocialIMEを使って漢字変換する。
japanizeType: GoogleIME

# Japanize変換されたメッセージ部分の、表示フォーマット設定。
# Japanize変換されないメッセージ（2バイト文字を含むなど）の場合は、適用されません。
japanizeLine1Format: '%msg &7(%japanize)'


# -------------------- tellコマンド設定 --------------------

# /tell や /msg や /r コマンドでプライベートメッセージを送信するときに、
# 適用されるフォーマット設定。
defaultFormatForPrivateMessage: '&7[%sender@%senderserver > %reciever@%recieverserver] %msg'


# -------------------- 広域チャット設定 --------------------

# 通常のチャット発言を、他のサーバーにも送信するかどうか。
# 注意：LunaChatなどで、サーバーローカルにチャンネルチャットを設定している場合、
#       チャンネルチャットへの発言内容も、全て他のサーバーに送信されてしまいます。
broadcastChat: false

# broadcastChat が true のときに、
# 他のサーバーで表示されるチャットフォーマットの設定。
broadcastChatFormat: '&d&lt;%sender@%senderserver> &f%msg'

# broadcastChat が true のときに、
# 発言者のサーバーについても、Japanize変換を行うかどうか。
# 各サーバーで、LunaChatなどJapanize変換を行うプラグインを使用したい場合は、
# false にしてください。
broadcastChatLocalJapanize: true
</pre>

※ ライセンス：LGPLv3を適用します。ソースコードを流用する場合は、流用先にもLGPLv3を適用してください。

※ 何かあったときの連絡先：<a href="https://twitter.com/ucchy99">ツイッター</a>へ連絡ください。

※ ダウンロード：<br/>
https://github.com/ucchyocean/BungeeJapanizeMessenger/blob/master/release/BungeeJapanizeMessenger.zip?raw=true
