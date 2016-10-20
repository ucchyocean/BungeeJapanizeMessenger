BungeeJapanizeMessenger
========================

BungeeCord を利用して複数サーバーを連結している環境において、サーバーをまたいだチャットやプライベートメッセージの送信を可能にします。
また、メッセージの内容がローマ字表記の場合は、漢字に変換して表示することが可能です。

<img src="https://github.com/ucchyocean/BungeeJapanizeMessenger/blob/master/release/bjm.png?raw=true" alt="スクリーンショット" width=427 height=240></img>


※特徴：
* /tell、/t、/msg、/message、/m、/w コマンドを置き換えて、会話相手のプレイヤーが別サーバーにいてもメッセージが送信できるようにします。
* /r または /reply コマンドで、/tell コマンドの返信を送信することができます。
* 広域チャット設定をオンにすると、全てのチャットメッセージを全サーバーに表示することができます。
* 「/dic add (単語) (変換後)」のコマンドで、日本語変換辞書に単語を登録することができます。
* あらかじめ設定されたNGワードをマスクすることができます。

※ 使用方法：<b>BungeeCordのpluginsフォルダ</b>に、jarファイルを入れてください。<br/>
<b>Bukkitやspigotのpluginsフォルダではありません！</b>

※ コマンド：/dictionary (省略形 /dic)
BungeeCordのパーミッション「bungeejapanizemessenger.dictionary」が無いと、実行できません。
あらかじめ BungeeCord の config.yml で、default グループの権限に、「bungeejapanizemessenger.dictionary」を与えておくとよいでしょう。
* /dic add (単語) (変換後) - 新しい単語を辞書に登録します。
* /dic remove (単語) - 登録されている単語を削除します。
* /dic view - 登録されている単語の一覧を表示します。

※ コンフィグ：plugins/BungeeJapanizeMessenger/config.yml に生成されます。
<pre>
# BungeeJapanizeMessenger v1.2.0
# @author     ucchy
# @license    LGPLv3
# @copyright  Copyright ucchy 2014


# -------------------- Japanize変換設定 --------------------

# ローマ字をかな文字や漢字に変換する設定。
# none/kana/GoogleIME の3つのいずれかが設定可能。
# none      : 変換なし。
# kana      : かな変換のみ。
# GoogleIME : かな変換したのち、GoogleIMEを使って漢字変換する。
japanizeType: GoogleIME

# かな変換や漢字変換時に、
# 変換結果を元の発言内容と合わせて1行で表示するか、
# 変換結果のみを2行目に表示するかを、設定する。
# 1行表示の例：
#     <ucchy> aiueo (あいうえお)
# 2行表示の例：
#     <ucchy> aiueo
#     [JP] あいうえお
japanizeDisplayLine: 1

# japanizeDisplayLine が 1 のときの、表示フォーマットを設定する。
# フォーマット設定には、下記のキーワードが使用できます。
# %msg      : 元の発言内容
# %japanize : Japanize変換された発言内容
japanizeLine1Format: '%msg &6(%japanize)'

# japanizeDisplayLine が 2 のときの、2行目の表示フォーマットを設定する。
# フォーマット設定には、下記のキーワードが使用できます。
# %msg      : 元の発言内容
# %japanize : Japanize変換された発言内容
japanizeLine2Format: '&6[JP] %japanize'

# ノンジャパナイズマーカー。
# これが発言の頭に入っている場合は、一時的にJapanize変換を実行しない。
noneJapanizeMarker: '#'

# チャンネルチャットの発言内容を、サーバーコンソールに表示するかどうか
displayChatOnConsole: true


# -------------------- tellコマンド設定 --------------------

# /tell や /msg や /r コマンドでプライベートメッセージを送信するときに、
# 適用されるフォーマット設定。
# フォーマット設定には、下記のキーワードが使用できます。
# %sender         : 発言者表示名
# %senderserver   : 発言者の接続サーバー名
# %reciever       : 受信者表示名
# %recieverserver : 受信者の接続サーバー名
# %msg            : 発言内容（Japanize変換された場合は、Japanize結果を含みます。）
defaultFormatForPrivateMessage: '&7[%sender@%senderserver > %reciever@%recieverserver] %msg'


# -------------------- 広域チャット設定 --------------------

# 通常のチャット発言を、他のサーバーにも送信するかどうか。
# 注意：LunaChatなどで、サーバーローカルにチャンネルチャットを設定している場合、
#       チャンネルチャットへの発言内容も、全て他のサーバーに送信されてしまいます。
broadcastChat: true

# broadcastChat が true のときに、
# 他のサーバーで表示されるチャットフォーマットの設定。
# フォーマット設定には、下記のキーワードが使用できます。
# %sender         : 発言者表示名
# %senderserver   : 発言者の接続サーバー名
# %date           : 発言した日付（BungeeCordのProxyサーバーのシステム時刻が使用されます）
# %time           : 発言した時刻（BungeeCordのProxyサーバーのシステム時刻が使用されます）
# %msg            : 発言内容（Japanize変換された場合は、Japanize結果を含みます。）
broadcastChatFormat: '%date %time &d<%sender@%senderserver> &f%msg'

# broadcastChat が true のときに、
# 発言者のサーバーについても、Japanize変換を行うかどうか。
# 各サーバーごとで、LunaChatなどのJapanize変換を行うプラグインを使用したい場合は、
# false にしてください。
broadcastChatLocalJapanize: true


# -------------------- NGワード設定 --------------------

# NGワード設定。正規表現が指定可能です。
# ここに設定されたワードを発言したプレイヤーは、NGワード部分がマスクされます。
ngword:
- ''

</pre>

※ ライセンス：LGPLv3を適用します。ソースコードを流用する場合は、流用先にもLGPLv3を適用してください。

※ 何かあったときの連絡先：<a href="https://twitter.com/ucchy99">ツイッター</a>へ連絡ください。

※ ダウンロード：<br/>
https://github.com/ucchyocean/BungeeJapanizeMessenger/blob/master/release/BungeeJapanizeMessenger-1.2.0-dist.zip?raw=true
